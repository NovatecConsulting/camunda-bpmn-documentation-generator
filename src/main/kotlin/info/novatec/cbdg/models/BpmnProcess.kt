package info.novatec.cbdg.models

import info.novatec.cbdg.models.elements.BpmnElement
import info.novatec.cbdg.models.elements.CallActivity

/**
 * A data class to hold information about a specific process inside a bpmn diagram.
 */
class BpmnProcess(
    id: String,
    name: String,
    version: String,
    documentation: String? = null,
    image: String?,
    callActivityList: List<CallActivity>
) : BpmnObject(id, name, version, documentation, image, callActivityList) {
    val elements: MutableList<BpmnElement> = mutableListOf()
    override fun toString(): String {
        return super.toString().replace("]", ", elements=$elements]")
    }
}
