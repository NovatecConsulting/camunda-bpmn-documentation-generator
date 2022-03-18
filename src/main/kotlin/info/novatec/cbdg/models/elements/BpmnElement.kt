package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.BpmnObject

/**
 * An abstract data class to hold information about any kind of element that's part of a BPMN process.
 */
abstract class BpmnElement(
    id: String,
    name: String,
    version: String,
    documentation: String?
) : BpmnObject(id, name, version, documentation, image = "", listOf())
