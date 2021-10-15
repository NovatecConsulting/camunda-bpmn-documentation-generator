package info.novatec.cbdg.plugin

import org.apache.commons.io.FileUtils
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File

@Mojo(name = "generate")
class GenerateMojo : AbstractMojo {
    constructor() : super()

    @Parameter(property = "camundaBpmnDir", defaultValue = "\${project.basedir}/src/main/resources/bpmn")
    val camundaBpmnDir = File("\${project.basedir}/src/main/resources/bpmn")

    @Parameter(property = "resultOutputDir", defaultValue = "\${project.build.directory}/classes/html")
    val resultOutputDir = File("\${project.build.directory}/classes/html")

    override fun execute() {
        camundaBpmnDir.listFiles().forEach { bpmnFile ->
            log.info(bpmnFile.absolutePath)
            FileUtils.writeStringToFile(File(resultOutputDir.absolutePath, bpmnFile.name + ".html"), "<html>" + bpmnFile.absolutePath + "</html>", "UTF-8")
        }
        resultOutputDir.listFiles().forEach { file ->
            log.info(file.absolutePath)
        }
    }
}
