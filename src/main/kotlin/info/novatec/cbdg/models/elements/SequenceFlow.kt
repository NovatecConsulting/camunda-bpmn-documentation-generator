package info.novatec.cbdg.models.elements

/**
 * The flow between two [BpmnElement]s, defining the connection between them.
 */
class SequenceFlow(
    id: String,
    name: String,
    version: String,
    documentation: String? = null
) : BpmnElement(id, name, version, documentation)
