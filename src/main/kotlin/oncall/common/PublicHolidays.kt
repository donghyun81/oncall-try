package oncall.common

enum class PublicHolidays(val month: Int, val day: Int) {
    NEW_YEAR_DAY(1, 1),
    MARCH_FIRST(3, 1),
    CHILDREN_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    LIBERATION_DAY(8, 15),
    FOUNDATION_DAY(10, 3),
    HANGUL_DAY(10, 9),
    CHRISTMAS(12, 25),
}