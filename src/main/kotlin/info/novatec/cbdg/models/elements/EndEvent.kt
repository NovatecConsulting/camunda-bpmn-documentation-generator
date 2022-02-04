package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.EndEventEnum

class EndEvent(
    id: String,
    name: String,
    version: String,
    documentation: String? = null,
    val type: EndEventEnum? = null
) : BpmnElement(id, name, version, documentation)
