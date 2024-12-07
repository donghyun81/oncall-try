package oncall

data class EmergencyDay(
    val day: Int,
    val dayOfWeek: DayOfWeek,
    val workerName: String,
    val isPublicDay: Boolean
)