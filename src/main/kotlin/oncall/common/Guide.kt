package oncall.common

enum class Guide(val message: String) {
    READ_MONTH_AND_DAT_OF_WEEK("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    READ_WEEKDAY_WORKERS("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    READ_HOLIDAY_WORKER("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
}