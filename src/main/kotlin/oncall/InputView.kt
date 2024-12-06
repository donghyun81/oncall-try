package oncall

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readOnCallMonthAndDayOfWeek(): Pair<Int, String> {
        val monthAndDayOfWeekInput = Console.readLine().split(",")
        require(monthAndDayOfWeekInput.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val (monthInput, dayOfWeekInput) = monthAndDayOfWeekInput
        val month = requireNotNull(monthInput.toIntOrNull()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(month in 1..12) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(dayOfWeekInput in DayOfWeek.entries.map { it.useName }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return Pair(month, dayOfWeekInput)
    }

    fun readWeekDayWorkers(): List<String> {
        val weekDayWorkers = Console.readLine().split(",")
        require(weekDayWorkers.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(weekDayWorkers.size == weekDayWorkers.distinct().size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        weekDayWorkers.forEach { worker ->
            require(worker.length <= 5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        }
        return weekDayWorkers
    }
}