package oncall

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readMonthAndDayOfWeek(): Pair<Int, String> {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine().split(",")
        require(input.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val (monthInput, dayOfWeek) = input
        val month = requireNotNull(monthInput.toIntOrNull()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(month in Month.entries.map { it.number }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(dayOfWeek in DayOfWeek.entries.map { it.useName }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return Pair(month, dayOfWeek)
    }

    fun readWeekDayWorkers(): List<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val workersName = Console.readLine().split(",")
        require(workersName.size == workersName.distinct().size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(workersName.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        workersName.forEach { require(it.length <= 5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." } }
        return workersName
    }

    fun readHolidayWorkers(): List<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val workersName = Console.readLine().split(",")
        require(workersName.size == workersName.distinct().size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(workersName.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        workersName.forEach { require(it.length <= 5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." } }
        println()
        return workersName
    }
}