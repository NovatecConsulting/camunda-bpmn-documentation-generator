import info.novatec.cbdg.helper.CommandLineHelper.Companion.runCommand
import info.novatec.docu.BpmnParser
import java.io.File

/**
 * blabla
 */
fun main() {
    val bpmnPath = "src/main/resources/bpmn/"
    val bpmnFile = "diagram1.bpmn"
    val bpmnFileAsPng = "diagram1.png"

    val bpmnObject = BpmnParser.parseBpmnFile(
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
}
