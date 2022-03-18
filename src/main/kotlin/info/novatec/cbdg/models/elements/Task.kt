package info.novatec.cbdg.models.elements

/**
 * An action that is part of a BPMN process sequence.
 */
class Task(
    id: String,
    name: String,
    version: String,
    documentation: String? = null
) : BpmnElement(id, name, version, documentation)
