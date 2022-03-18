package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.GatewayTypeEnum

/**
 * A gateway inside a BPMN process that decides which option happens next.
 * @property type defines the kind of gateway.
 */
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
