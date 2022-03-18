package info.novatec.cbdg.models

import info.novatec.cbdg.models.elements.CallActivity

/**
 * An abstract data class to hold information extracted from the BPMN input model.
 * @property id the ID of the BPMN object.
 * @property name the name of the BPMN object, not necessarily equal to the ID.
 * @property version the version tag of the BPMN object
 * @property documentation the element documentation of the BPMN object
 * @property image the name of the generated image of the BPMN process.
 */
abstract class BpmnObject(
    val id: String,
    val name: String,
    val version: String,
    val documentation: String? = null,
    val image: String?,
    val callActivityList: List<CallActivity>
) {
    override fun toString(): String {
        return "${javaClass.name}[id=$id, name=$name, version=$version, documentation=$documentation, image=$image, callActivityList=$callActivityList]"
    }
}
