package oncall

enum class DayOfWeek(val useName: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    fun nextDayOfWeek(): DayOfWeek {
        val nextDayOfWeekIndex = (entries.indexOfFirst { it.useName == this.useName }+1) % 7
        return entries[nextDayOfWeekIndex]
    }

    companion object {
        fun convertDayOfWeek(dayOfWeekUseName: String): DayOfWeek {
            val dayOfWeek = DayOfWeek.entries.find { it.useName == dayOfWeekUseName }
            return requireNotNull(dayOfWeek) { "[ERROR] 없는 요일 입니다." }
        }
    }
}