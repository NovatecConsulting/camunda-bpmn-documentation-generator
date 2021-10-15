import info.novatec.docu.BpmnParser
import java.io.File

fun main() {
    println("Hello Bpmn Documenters!")
    val bpmnObject = BpmnParser.parseBpmnFile(File("src/main/resources/diagram1.bpmn"))
    println(bpmnObject.id)
    println(bpmnObject.name)
    println(bpmnObject.version)
    println(bpmnObject.documentation)
}
