package oncall

data class EmergencyDay(val date: Int, val dayOfWeek: DayOfWeek, val worker: Worker, val isPublicDay: Boolean = false) {
    fun isHolidayWork() = dayOfWeek.isHoliday() || isPublicDay
}