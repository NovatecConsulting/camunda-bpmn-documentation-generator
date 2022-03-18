package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.EventEnum

/**
 * An event that defines a possible start of a [info.novatec.cbdg.models.BpmnProcess].
 * @property [type] defines what kind of event leads to the process start, e.g. an incoming signal or message, the end of a timer or any other kind of condition.
 */
class StartEvent(
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
