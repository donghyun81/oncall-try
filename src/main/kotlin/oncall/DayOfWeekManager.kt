package oncall

class DayOfWeekManager(startDayOfWeek: DayOfWeek) {
    private var dayOfWeek = startDayOfWeek

    fun isHoliday(): Boolean {
        return dayOfWeek.useName in DayOfWeek.entries.subList(5, 7).map { it.useName }
    }

    fun getCurrentDayOfWeek(): DayOfWeek {
        val currentDayOfWeek = dayOfWeek
        dayOfWeek = currentDayOfWeek.nextDayOfWeek()
        return currentDayOfWeek
    }
}