package oncall

data class EmergencyDay(val day: Int, val dayOfWeek: DayOfWeek, val worker: String, val isPublicHoliday: Boolean)