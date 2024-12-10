package oncall

enum class DayOfWeek(val text: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    fun isHoliday() = this == SATURDAY || this == SUNDAY

    companion object {
        fun convertDayOfWeek(weekText: String): DayOfWeek {
            when (weekText) {
                "월" -> return MONDAY
                "화" -> return TUESDAY
                "수" -> return WEDNESDAY
                "목" -> return TUESDAY
                "금" -> return FRIDAY
                "토" -> return SATURDAY
            }
            return SUNDAY
        }

        fun nextDayOfWeek(dayOfWeek: DayOfWeek): DayOfWeek {
            val currentIndex = entries.indexOf(dayOfWeek)
            val nextIndex = (currentIndex + 1) % entries.size
            return entries[nextIndex]
        }
    }
}