package info.novatec.docu.parser.element

import info.novatec.cbdg.models.elements.CallActivity
import info.novatec.constants.BPMN_ATTRIBUTE_CALLED_ELEMENT
import info.novatec.constants.BPMN_TAG_CALL_ACTIVITY
import org.camunda.bpm.model.bpmn.instance.Process

object CallActivityParser {

    /**
     * Extracts all call activities from a process, maps them to a [CallActivity] and puts them in a list.
     *
     * @param process The process from which all CallActivities are extracted to a [CallActivity] object and put in the returned list
     *
     * @return the list of CallActivities as [CallActivity] object with the information about Id, Name and the Called Element and Element Documentation,
     * comprised in one object.
     */
    fun parseToCallActivityList(process: Process): List<CallActivity> {
        val callActivityFlowElementList = process.flowElements.filter { it.elementType.typeName.equals(BPMN_TAG_CALL_ACTIVITY) }
        return callActivityFlowElementList
            .map {
                CallActivity(
                    it.id, // Id of CallActivity
                    it.name, // Name of CallActivity
                    process.camundaVersionTag, // Version Tag of Process
                    it.documentations.stream().findFirst().map { doc -> doc.textContent.toString() }.orElse(null), // Element Documentation of CallActivity
                    it.domElement.getAttribute(BPMN_ATTRIBUTE_CALLED_ELEMENT) // Called Element of CallAcitivity
                )
            }
    }
}
