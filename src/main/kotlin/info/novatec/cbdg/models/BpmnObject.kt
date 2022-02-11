package info.novatec.cbdg.models

import info.novatec.cbdg.models.elements.CallActivity

/**
 * A data class to hold information extracted from the BPMN input model.
 * @param id the ID of the BPMN object.
 * @param name the name of the BPMN object, not necessarily equal to the ID.
 * @param version the version tag of the BPMN object
 * @param documentation the element documentation of the BPMN object
 * @param image the name of the generated image of the BPMN process.
 * @param callActivityList a list of all CallActivities in the BPMN object
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
