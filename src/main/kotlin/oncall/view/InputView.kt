package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.common.*

class InputView {
    fun readMonthAndDayOfWeek(): Pair<Int, String> {
        print(Guide.READ_MONTH_AND_DAT_OF_WEEK.message)
        val input = Console.readLine().split(DEFAULT_SEPARATOR)
        require(input.size == PAIR_SIZE) { Error.FORMAT.getMessage() }
        val (monthInput, dayOfWeek) = input
        val month = requireNotNull(monthInput.toIntOrNull()) { Error.MONTH.getMessage() }
        require(month in Month.entries.map { it.number }) { Error.MONTH.getMessage() }
        require(dayOfWeek in DayOfWeek.entries.map { it.useName }) { Error.DAY_OF_WEEK.getMessage() }
        return Pair(month, dayOfWeek)
    }

    fun readWorkers(guideMessage: String): List<String> {
        print(guideMessage)
        val workersName = Console.readLine().split(DEFAULT_SEPARATOR)
        return workersName
    }

    companion object {
        private const val PAIR_SIZE = 2
    }
}