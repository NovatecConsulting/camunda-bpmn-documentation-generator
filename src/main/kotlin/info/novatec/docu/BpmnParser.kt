package info.novatec.docu

import info.novatec.cbdg.models.BpmnObject
import info.novatec.cbdg.models.BpmnProcess
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.instance.Process
import java.io.File

object BpmnParser {

    /**
     * Parses a bpmn file into a BpmnObject.
     *
     * @param bpmnFile the BPMN input file that should be parsed.
     * @param bpmnImagePath path to a manually generated image of the BPMN diagram that will be included in the generated output.
     * Viable formats: png, svg.
     */
    fun parseBpmnFile(bpmnFile: File, bpmnImagePath: String): BpmnObject {
        val bpmnModelInstance = Bpmn.readModelFromFile(bpmnFile)
        val model = bpmnModelInstance.model
        val process: Process = bpmnModelInstance.getModelElementsByType(model.getType(Process::class.java)).stream()
            .findFirst().get() as Process
        return BpmnProcess(
            process.id,
            process.name,
            process.camundaVersionTag,
            process.documentations.stream().findFirst().orElse(null)?.textContent.toString(),
            bpmnImagePath
        )
    }
}
