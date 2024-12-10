package oncall

enum class Month(val number: Int, val startDay: Int, val endDay: Int) {
    JANUARY(1, 1, 31),
    FEBRUARY(2, 1, 28),
    MARCH(3, 1, 31),
    APRIL(4, 1, 30),
    MAY(5, 1, 31),
    JUNE(6, 1, 30),
    JULY(7, 1, 31),
    AUGUST(8, 1, 31),
    SEPTEMBER(9, 1, 30),
    OCTOBER(10, 1, 31),
    NOVEMBER(11, 1, 30),
    DECEMBER(12, 1, 31);

    companion object {
        fun convertMonth(monthNumber: Int): Month {
            val month = Month.entries.find { it.number == monthNumber }
            return requireNotNull(month) {"[ERROR] 없는 월 입니다."}
        }
    }
}
