package info.novatec.cbdg.plugin

import info.novatec.cbdg.FreeMarkerService
import info.novatec.docu.parser.main.BpmnParser
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

/**
 * Mojo - Class for cbdg-plugin. Calls by Maven-command 'mvn cbdg:generate'.
 */
@Mojo(name = "generate")
class GenerateMojo : AbstractMojo() {

    /**
     * Default usage is the templates/default.ftl from Jar-File.
     * To use it, it will be created in Build-Dir of the target project the empty file default.ftl
     * and fill it with the stream of templates/default.ftl from Jar-File
     */
    @Parameter(property = "templateFile", defaultValue = "\${project.build.directory}/classes/templates/default.ftl")
    lateinit var templateFile: File

    /**
     * Directory with bpmn-files. Default is '{project.basedir}/src/main/resources/bpmn'
     */
    @Parameter(property = "camundaBpmnDir", defaultValue = "\${project.basedir}/src/main/resources/bpmn")
    lateinit var camundaBpmnDir: File

    /**
     * Target-directory fot generated content. Default is '{project.build.directory}/cbdg/html'
     */
    @Parameter(property = "resultOutputDir", defaultValue = "\${project.build.directory}/cbdg/html")
    lateinit var resultOutputDir: File

    /**
     * Directory with the images of bpmn-files. Default is '{project.basedir}/src/main/resources/images'
     */
    @Parameter(property = "bpmnDiagramImageDir", defaultValue = "\${project.basedir}/src/main/resources/images")
    var bpmnDiagramImageDir: File? = null

    override fun execute() {
        if (templateFile.name.equals("default.ftl")) {
            FileOutputStream(templateFile, false).use { it -> javaClass.classLoader.getResourceAsStream("templates/default.ftl").transferTo(it) }
        }

        camundaBpmnDir.listFiles()?.forEach {
            log.info("Generating documentation for file ${it.absolutePath}")
            log.info("Using template ${templateFile.absolutePath}")

            val imageSrcPath = Path("${bpmnDiagramImageDir?.absolutePath}/${it.nameWithoutExtension}.png")
            val imageTargetPath = Path("${resultOutputDir.absolutePath}/images/${it.nameWithoutExtension}.png")
            imageTargetPath.parent.createDirectories()
            if (imageSrcPath.exists()) {
                Files.copy(imageSrcPath, imageTargetPath, StandardCopyOption.REPLACE_EXISTING)
            }

            val bpmnObject = BpmnParser.parseBpmnFile(it, "${it.nameWithoutExtension}.png")
            FreeMarkerService.writeTemplate(
                bpmnObject,
                templateFile.name,
                "${resultOutputDir.absolutePath}/${it.nameWithoutExtension}.html"
            ) {
                setDirectoryForTemplateLoading(templateFile.parentFile)
            }
            log.info("Output report into path ${resultOutputDir.absolutePath}")
        } ?: throw FileNotFoundException("${camundaBpmnDir.absolutePath} don't exist.")
        resultOutputDir.listFiles()?.forEach {
            log.info(it.absolutePath)
        } ?: throw FileNotFoundException("${resultOutputDir.absolutePath} don't exist.")
    }
}
