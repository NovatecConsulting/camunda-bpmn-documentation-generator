package info.novatec.cbdg.models.elements

import info.novatec.cbdg.models.enums.GatewayTypeEnum

class Gateway(id: String, name: String, version: String, documentation: String? = null, type: GatewayTypeEnum? = null) : BpmnElement(id, name, version, documentation)
