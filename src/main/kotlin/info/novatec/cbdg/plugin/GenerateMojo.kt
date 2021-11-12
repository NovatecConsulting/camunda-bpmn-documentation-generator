package info.novatec.cbdg.plugin

import org.apache.commons.io.FileUtils
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File
import java.io.FileNotFoundException

@Mojo(name = "generate")
class GenerateMojo : AbstractMojo {
    constructor() : super()

    @Parameter(property = "camundaBpmnDir", defaultValue = "\${project.basedir}/src/main/resources/bpmn")
    lateinit var camundaBpmnDir: File

    @Parameter(property = "resultOutputDir", defaultValue = "\${project.build.directory}/cbdg/html")
    lateinit var resultOutputDir: File

    override fun execute() {
        camundaBpmnDir.listFiles()?.forEach {
            log.info(it.absolutePath)
            FileUtils.writeStringToFile(File(resultOutputDir.absolutePath, it.name + ".html"), "<html>" + it.absolutePath + "</html>", "UTF-8")
        } ?: throw FileNotFoundException("${camundaBpmnDir.absolutePath} don't exist.")
        resultOutputDir.listFiles()?.forEach {
            log.info(it.absolutePath)
        } ?: throw FileNotFoundException("${resultOutputDir.absolutePath} don't exist.")
    }
}
