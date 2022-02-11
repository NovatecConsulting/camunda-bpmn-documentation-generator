package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.EventEnum

class EndEvent(id: String, name: String, version: String, documentation: String? = null, val type: EventEnum) :
    BpmnElement(id, name, version, documentation)
