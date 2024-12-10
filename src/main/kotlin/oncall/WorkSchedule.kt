package oncall

data class WorkSchedule(
    val month: Int,
    val emergencyDays: List<EmergencyDay>
)