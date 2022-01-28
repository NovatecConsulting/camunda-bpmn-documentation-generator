package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.StartEventEnum

class StartEvent(id: String, name: String, version: String, documentation: String? = null, val type: StartEventEnum? = null) :
    BpmnElement(id, name, version, documentation)
