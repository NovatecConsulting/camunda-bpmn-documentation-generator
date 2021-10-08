import models.BpmnObject

fun main() {
    println("Hello World!")
    val testObject = BpmnObject("TestId", "Test-Object")
    FreeMarkerService.writeTemplate(mapOf("bpmn" to testObject), "basic.ftl", outputPath = "out/reports/basicGeneratedOutput.html")
}