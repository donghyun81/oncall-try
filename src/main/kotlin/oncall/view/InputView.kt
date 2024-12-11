package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.common.DayOfWeek
import oncall.common.Error
import oncall.common.Month

class InputView {
    fun readMonthAndDayOfWeek(): Pair<Int, String> {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine().split(",")
        require(input.size == 2) { Error.FORMAT.getMessage() }
        val (monthInput, dayOfWeek) = input
        val month = requireNotNull(monthInput.toIntOrNull()) { Error.MONTH.getMessage() }
        require(month in Month.entries.map { it.number }) { Error.MONTH.getMessage() }
        require(dayOfWeek in DayOfWeek.entries.map { it.useName }) { Error.DAY_OF_WEEK.getMessage() }
        return Pair(month, dayOfWeek)
    }

    fun readWeekDayWorkers(): List<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val workersName = Console.readLine().split(",")
        require(workersName.size == workersName.distinct().size) { Error.DUPLICATE.getMessage() }
        require(workersName.size in 5..35) { Error.WORKERS_COUNT.getMessage() }
        workersName.forEach { require(it.length <= 5) { Error.WORKER_NAME_LENGTH.getMessage() } }
        return workersName
    }

    fun readHolidayWorkers(): List<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val workersName = Console.readLine().split(",")
        require(workersName.size == workersName.distinct().size) { Error.DUPLICATE.getMessage() }
        require(workersName.size in 5..35) { Error.WORKERS_COUNT.getMessage() }
        workersName.forEach { require(it.length <= 5) { Error.WORKER_NAME_LENGTH.getMessage() } }
        println()
        return workersName
    }
}