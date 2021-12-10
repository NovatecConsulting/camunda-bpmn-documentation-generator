package info.novatec.cbdg.models

import info.novatec.cbdg.models.elements.BpmnElement

class BpmnProcess(id: String, name: String, version: String, documentation: String? = null) : BpmnObject(id, name, version, documentation) {
    val elements: MutableList<BpmnElement> = mutableListOf()
    override fun toString(): String {
        return super.toString().replace("]", ", elements=$elements]")
    }
}
