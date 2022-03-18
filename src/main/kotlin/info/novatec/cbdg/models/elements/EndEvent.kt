package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.EventEnum

/**
 * An event that happens at a possible end of a BPMN process.
 * @property type defines what happens, e.g. sending a signal to another process, terminating the process or compensating the user for an error during the process.
 */
class EndEvent(
    id: String,
    name: String,
    version: String,
    documentation: String? = null,
    val type: EventEnum
) : BpmnElement(id, name, version, documentation) {
    override fun toString(): String {
        return super.toString().replace("]", ", type=$type]")
    }
}
