package info.novatec.docu.parser.main

import info.novatec.cbdg.models.BpmnObject
import info.novatec.cbdg.models.BpmnProcess
import info.novatec.cbdg.models.elements.EndEvent
import info.novatec.cbdg.models.elements.StartEvent
import info.novatec.cbdg.models.elements.Task
import info.novatec.cbdg.models.enums.EventEnum
import info.novatec.docu.parser.element.CallActivityParser.parseToCallActivityList
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.instance.Documentation
import org.camunda.bpm.model.bpmn.instance.EventDefinition
import org.camunda.bpm.model.bpmn.instance.FlowElement
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
    fun parseBpmnFile(
        bpmnFile: File,
        bpmnImagePath: String?
    ): BpmnObject {
        val bpmnModelInstance = Bpmn.readModelFromFile(bpmnFile)
        val model = bpmnModelInstance.model
        val process: Process = bpmnModelInstance.getModelElementsByType(model.getType(Process::class.java)).stream()
            .findFirst().get() as Process

        val result = BpmnProcess(
            process.id,
            process.name,
            process.camundaVersionTag,
            process.documentations.stream().findFirst().orElse(null)?.textContent.toString(),
            bpmnImagePath,
            parseToCallActivityList(process)
        )

        result.elements.addAll(determineStartEvents(process))
        result.elements.addAll(determineEndEvents(process))
        result.tasks = determineTasks(process)

        return result
    }

    private fun determineStartEvents(
        process: Process
    ): List<StartEvent> {
        return process.flowElements.filter { it.elementType.typeName.equals("startEvent") }
            .map {
                StartEvent(
                    it.id,
                    it.name ?: "",
                    process.camundaVersionTag,
                    it.documentations.stream().findFirst().orElse(null)?.textContent.toString(),
                    mapEventDefinition(it)
                )
            }
    }

    private fun determineEndEvents(
        process: Process
    ): List<EndEvent> {
        return process.flowElements.filter { it.elementType.typeName.equals("endEvent") }
            .map {
                EndEvent(
                    it.id,
                    it.name ?: "",
                    process.camundaVersionTag,
                    it.documentations.stream().findFirst().orElse(null)?.textContent.toString(),
                    mapEventDefinition(it)
                )
            }
    }

    private fun mapEventDefinition(it: FlowElement) =
        EventEnum.values().asList().stream().filter { event: EventEnum ->
            it.getChildElementsByType(EventDefinition::class.java).stream().anyMatch { event.eventType == it.elementType.typeName }
        }.findFirst().orElseGet { EventEnum.UNSPECIFIED }

    private fun determineTasks(process: Process): List<Task> {
        return process.flowElements.filterIsInstance<org.camunda.bpm.model.bpmn.instance.Task>()
            .map {
                Task(
                    it.id,
                    it.name ?: "",
                    process.camundaVersionTag,
                    it.documentations.joinToString("; ", transform = Documentation::getTextContent)
                )
            }
    }
}
