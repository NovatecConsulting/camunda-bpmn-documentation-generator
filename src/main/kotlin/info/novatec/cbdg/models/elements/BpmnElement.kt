package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.BpmnObject

abstract class BpmnElement(
    id: String,
    name: String,
    version: String,
    documentation: String?
) : BpmnObject(id, name, version, documentation, image = "", listOf())
