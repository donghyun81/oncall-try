package oncall

import java.time.LocalDate

class OnCallController {
    private val inputView = InputView()

    fun run() {
        val (month, startDayOfWeek) = getEmergencyWorkDays()
        val weekDayWorkers = getWeekdayWorkers()
        val holidayWorkers = getHolidayWorkers()

    }

    private fun getEmergencyWorkDays(): Pair<Month, String> {
        val emergencyWorkDaysInput = inputView.readEmergencyWorkDays().split(",")
        require(emergencyWorkDaysInput.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val (emergencyMonthInput, startDayOfWeekInput) = emergencyWorkDaysInput
        val monthNumber =
            requireNotNull(emergencyMonthInput.toIntOrNull()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(monthNumber in 1..12) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(startDayOfWeekInput in WEEK.entries.map { it.text }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return Pair(Month.convertMonth(monthNumber), startDayOfWeekInput)
    }

    private fun getWeekdayWorkers() = inputView.readWeekdayWorker().split(",").map { Worker(it, false) }

    private fun getHolidayWorkers() = inputView.readHolidayWorker().split(",").map { Worker(it, true) }

    private fun getEmergencyMonth(worker: List<Worker>) {

    }
}