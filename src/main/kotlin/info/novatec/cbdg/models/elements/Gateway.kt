package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.GatewayTypeEnum

class Gateway(
    id: String,
    name: String,
    version: String,
    documentation: String? = null,
    var type: GatewayTypeEnum? = null
) : BpmnElement(id, name, version, documentation) {
    override fun toString(): String {
        return super.toString().replace("]", ", type=$type]")
    }
}
