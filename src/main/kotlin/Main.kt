import info.novatec.cbdg.FreeMarkerService
import info.novatec.cbdg.helper.CommandLineHelper.Companion.runCommand
import info.novatec.docu.parser.main.BpmnParser
import java.io.File

fun main() {
    val bpmnPath = "src/test/resources/bpmn/"
    val bpmnFile = "TestDiagram.bpmn"
    val bpmnFileAsPng = "TestDiagram.png"

    val bpmnObject = BpmnParser().parseBpmnFile(
        File(bpmnPath + bpmnFile),
        bpmnPath + bpmnFileAsPng
    )

    ("bpmn-to-image $bpmnFile:$bpmnFileAsPng") runCommand (File(bpmnPath))

    println("Hello Bpmn Documenters!")
    println(bpmnObject.id)
    println(bpmnObject.name)
    println(bpmnObject.version)
    println(bpmnObject.documentation)
    println(bpmnObject.image)

    FreeMarkerService.writeTemplate(bpmnObject, "default.ftl", outputPath = "out/reports/processGeneratedOutput.html") {
        setDirectoryForTemplateLoading(File("./src/main/resources/templates/"))
    }
}
