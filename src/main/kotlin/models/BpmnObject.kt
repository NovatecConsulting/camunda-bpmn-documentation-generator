package models

/**
 * A data class to hold information extracted from the BPMN input model.
 * @param id the ID of the BPMN object.
 * @param name the name of the BPMN object, not necessarily equal to the ID.
 */
data class BpmnObject(val id: String, val name: String)
