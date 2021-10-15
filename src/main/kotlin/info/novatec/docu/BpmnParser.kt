package info.novatec.docu

import models.BpmnObject
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.instance.Process
import java.io.File

object BpmnParser {

    fun parseBpmnFile(bpmnFile: File): BpmnObject {
        val bpmnModelInstance = Bpmn.readModelFromFile(bpmnFile)
        val model = bpmnModelInstance.model
        val process: Process = bpmnModelInstance.getModelElementsByType(model.getType(Process::class.java)).stream()
            .findFirst().get() as Process
        return BpmnObject(
            process.id,
            process.name,
            process.camundaVersionTag,
            process.documentations.stream().findFirst().orElse(null)?.textContent.toString()
        )
    }
}
