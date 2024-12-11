package oncall

class WorkScheduleService(private val month: Month, private val startDayOfWeek: DayOfWeek) {
    private var currentDayOfWeek = startDayOfWeek

    fun getEmergencyDays(month: Month, workerService: WorkerService): List<EmergencyDay> {
        val days = month.startDay..month.endDay
        val emergencyDays = days.map { day ->
            val isDayOff = isDayOff(month.number, day)
            val worker = workerService.getWorker(isDayOff)
            val emergencyDay = EmergencyDay(day, currentDayOfWeek, worker, isPublicHoliday(month.number, day))
            moveNextDayOfWeek()
            emergencyDay
        }
        return emergencyDays
    }

    private fun moveNextDayOfWeek() {
        val nextDayOfWeekIndex = DayOfWeek.entries.indexOfFirst { it == currentDayOfWeek } + 1 % 7
        currentDayOfWeek = DayOfWeek.entries[nextDayOfWeekIndex]
    }

    private fun isDayOff(monthNumber: Int, day: Int): Boolean {
        val holiday = DayOfWeek.entries.subList(5, 7)
        val isHoliday = currentDayOfWeek in holiday
        val isPublicHoliday = isPublicHoliday(monthNumber, day)
        return isHoliday || isPublicHoliday
    }

    private fun isPublicHoliday(monthNumber: Int, day: Int) =
        PublicHolidays.entries.any { it.day == day && it.month == monthNumber }
}