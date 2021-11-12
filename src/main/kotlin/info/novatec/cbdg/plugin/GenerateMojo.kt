package info.novatec.cbdg.plugin

import models.BpmnObject
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File
import java.io.FileNotFoundException

@Mojo(name = "generate")
class GenerateMojo : AbstractMojo() {

    @Parameter(property = "templateFile", defaultValue = "\${project.basedir}/src/main/resources/templates/basic.ftl")
    lateinit var templateFile: File

    @Parameter(property = "camundaBpmnDir", defaultValue = "\${project.basedir}/src/main/resources/bpmn")
    lateinit var camundaBpmnDir: File

    @Parameter(property = "resultOutputDir", defaultValue = "\${project.build.directory}/cbdg/html")
    lateinit var resultOutputDir: File

    override fun execute() {
        camundaBpmnDir.listFiles()?.forEach {
            log.info("Generating documentation for file ${it.absolutePath}")
            log.info("Using template ${templateFile.absolutePath}")
            val bpmnObject = BpmnObject(id = "test-id", it.name)
            FreeMarkerService.writeTemplate(bpmnObject, templateFile.name, "${resultOutputDir.absolutePath}/${it.nameWithoutExtension}.html") {
                setDirectoryForTemplateLoading(templateFile.parentFile)
            }
            log.info("Output report into path ${resultOutputDir.absolutePath}")
        } ?: throw FileNotFoundException("${camundaBpmnDir.absolutePath} don't exist.")
        resultOutputDir.listFiles()?.forEach {
            log.info(it.absolutePath)
        } ?: throw FileNotFoundException("${resultOutputDir.absolutePath} don't exist.")
    }
}
