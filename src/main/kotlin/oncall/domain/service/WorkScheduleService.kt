package oncall.domain.service

import oncall.common.DayOfWeek
import oncall.model.EmergencyDay
import oncall.common.Month
import oncall.common.NEXT_INDEX
import oncall.common.PublicHolidays

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
        val dayOfWeeks = DayOfWeek.entries
        val nextDayOfWeekIndex = (dayOfWeeks.indexOfFirst { it == currentDayOfWeek } + NEXT_INDEX) % dayOfWeeks.size
        currentDayOfWeek = DayOfWeek.entries[nextDayOfWeekIndex]
    }

    private fun isDayOff(monthNumber: Int, day: Int): Boolean {
        val isHoliday = currentDayOfWeek == DayOfWeek.SATURDAY || currentDayOfWeek == DayOfWeek.SUNDAY
        val isPublicHoliday = isPublicHoliday(monthNumber, day)
        return isHoliday || isPublicHoliday
    }

    private fun isPublicHoliday(monthNumber: Int, day: Int) =
        PublicHolidays.entries.any { it.day == day && it.month == monthNumber }
}