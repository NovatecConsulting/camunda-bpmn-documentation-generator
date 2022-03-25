package info.novatec.cbdg.models.enums

enum class EventEnum(val eventType: String) {
    UNSPECIFIED(""),
    MESSAGE("messageEventDefinition"),
    TIMER("timerEventDefinition"),
    SIGNAL("signalEventDefinition"),
    CONDITIONAL("conditionalEventDefinition"),
    ESCALATION("escalationEventDefinition"),
    ERROR("errorEventDefinition"),
    COMPENSATION("compensateEventDefinition"),
    TERMINATE("terminateEventDefinition")
}
