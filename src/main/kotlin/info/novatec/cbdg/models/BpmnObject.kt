package info.novatec.cbdg.models

/**
 * A data class to hold information extracted from the BPMN input model.
 * @param id the ID of the BPMN object.
 * @param name the name of the BPMN object, not necessarily equal to the ID.
 * @param version the version tag of the BPMN object
 * @param documentation the element documentation of the BPMN object
 */
abstract class BpmnObject(val id: String, val name: String, val version: String, val documentation: String? = null) {
    override fun toString(): String {
        return "${javaClass.name}[id=$id, name=$name, version=$version, documentation=$documentation]"
    }
}
