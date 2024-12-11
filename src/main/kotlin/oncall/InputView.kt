package oncall

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readMonthAndDayOfWeek(): Pair<Int, String> {
        val input = Console.readLine().split(",")
        require(input.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val (monthInput, dayOfWeek) = input
        val month = requireNotNull(monthInput.toIntOrNull()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(month in Month.entries.map { it.number }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(dayOfWeek in DayOfWeek.entries.map { it.useName }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return Pair(month, dayOfWeek)
    }

    fun readWeekDayWorkers(): List<String> {
        val workersName = Console.readLine().split(",")
        require(workersName.size == workersName.distinct().size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(workersName.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return workersName
    }

    fun readHolidayWorkers(): List<String> {
        val workersName = Console.readLine().split(",")
        require(workersName.size == workersName.distinct().size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(workersName.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return workersName
    }
}