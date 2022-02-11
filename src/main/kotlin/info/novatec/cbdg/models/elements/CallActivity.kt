package info.novatec.cbdg.models.elements

/**
 * Model class for call activities.
 * A call activity allows you to call and invoke another process as part of this process.
 *
 * @param id The id of the call activity
 * @param name The name of the call activity
 * @param version The version number of the call activity
 * @param documentation The content of the documentation that belongs to the call activity
 * @param calledElement The element that is called out of the call activity, e.g. a new process instance
 */
class CallActivity(id: String, name: String, version: String, documentation: String?, val calledElement: String? = null) :
    BpmnElement(id, name, version, documentation)
