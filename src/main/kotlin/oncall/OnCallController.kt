package oncall

class OnCallController {
    private val inputView = InputView()

    fun run() {
        val (month, startDayOfWeek) = getEmergencyWorkDays()
        val weekDayWorkers = getWeekdayWorkers()
        val holidayWorkers = getHolidayWorkers()
        val emergencyMonth = getEmergencyMonth(weekDayWorkers, holidayWorkers, month, startDayOfWeek)
    }

    private fun getEmergencyWorkDays(): Pair<Month, DayOfWeek> {
        val emergencyWorkDaysInput = inputView.readEmergencyWorkDays().split(",")
        require(emergencyWorkDaysInput.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        val (emergencyMonthInput, startDayOfWeekInput) = emergencyWorkDaysInput
        val monthNumber =
            requireNotNull(emergencyMonthInput.toIntOrNull()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(monthNumber in 1..12) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(startDayOfWeekInput in DayOfWeek.entries.map { it.text }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        return Pair(Month.convertMonth(monthNumber), DayOfWeek.convertDayOfWeek(startDayOfWeekInput))
    }

    private fun getWeekdayWorkers() = inputView.readWeekdayWorker().split(",").map { Worker(it, false) }

    private fun getHolidayWorkers() = inputView.readHolidayWorker().split(",").map { Worker(it, true) }

    private fun getEmergencyMonth(
        weekDayWorkers: List<Worker>,
        holidayWorkers: List<Worker>,
        month: Month,
        startDayOfWeek: DayOfWeek
    ) = EmergencyMonth(month, makeEmergencyDays(weekDayWorkers, holidayWorkers, month, startDayOfWeek))

    private fun makeEmergencyDays(
        weekDayWorkers: List<Worker>,
        holidayWorkers: List<Worker>,
        month: Month,
        startDayOfWeek: DayOfWeek
    ): List<EmergencyDay> {
        var currentDayOfWeek = startDayOfWeek
        var currentWeekDayWorkersIndex = 0
        var currentHolyDayWorkersIndex = 0
        return month.days.map { day ->
            val dayOfWeek = currentDayOfWeek
            if (currentDayOfWeek.text == "토" || currentDayOfWeek.text == "일") {
                val holidayWorkersIndex = currentHolyDayWorkersIndex % holidayWorkers.size
                currentHolyDayWorkersIndex++
                currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek)
                EmergencyDay(day, dayOfWeek, holidayWorkers[holidayWorkersIndex])
            } else {
                val weekDayWorkersIndex = currentHolyDayWorkersIndex % holidayWorkers.size
                currentWeekDayWorkersIndex++
                EmergencyDay(day, dayOfWeek, weekDayWorkers[weekDayWorkersIndex])
            }
        }
    }
}