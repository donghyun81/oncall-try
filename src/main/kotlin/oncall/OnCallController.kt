package oncall

class OnCallController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val (month, startDayOfWeek) = getEmergencyWorkDays()
        val weekDayWorkers = getWeekdayWorkers()
        val holidayWorkers = getHolidayWorkers()
        val emergencyMonth = getEmergencyMonth(weekDayWorkers, holidayWorkers, month, startDayOfWeek)
        outputView.printResult(emergencyMonth)
    }

    private fun getEmergencyWorkDays(): Pair<Month, DayOfWeek> {
        return retryInput {
            val emergencyWorkDaysInput = inputView.readEmergencyWorkDays().split(",")
            require(emergencyWorkDaysInput.size == 2) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            val (emergencyMonthInput, startDayOfWeekInput) = emergencyWorkDaysInput
            val monthNumber =
                requireNotNull(emergencyMonthInput.toIntOrNull()) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            require(monthNumber in 1..12) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            require(startDayOfWeekInput in DayOfWeek.entries.map { it.text }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            Pair(Month.convertMonth(monthNumber), DayOfWeek.convertDayOfWeek(startDayOfWeekInput))
        }
    }

    private fun getWeekdayWorkers() = retryInput {
        val weekdayWorkers = inputView.readWeekdayWorker().split(",").map { Worker(it) }
        require(weekdayWorkers.distinct().size == weekdayWorkers.size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(weekdayWorkers.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        weekdayWorkers.forEach { worker ->
            require(worker.name.length <= 5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            worker.name.length
        }
        weekdayWorkers
    }


    private fun getHolidayWorkers() = retryInput {
        val holidayWorkers = inputView.readHolidayWorker().split(",").map { Worker(it) }
        require(holidayWorkers.distinct().size == holidayWorkers.size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        require(holidayWorkers.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
        holidayWorkers.forEach { worker ->
            require(worker.name.length <= 5) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            worker.name.length
        }
        holidayWorkers
    }

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
            val isPublicHoliday = PublicHolidays.containsPublicHolidays(month.month, day)
            if (currentDayOfWeek.isHoliday() || isPublicHoliday) {
                val holidayWorker = holidayWorkers[currentHolyDayWorkersIndex % holidayWorkers.size]
                currentHolyDayWorkersIndex++
                currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek)
                EmergencyDay(day, dayOfWeek, holidayWorker, isPublicHoliday)
            } else {
                val weekDayWorker = weekDayWorkers[currentWeekDayWorkersIndex % holidayWorkers.size]
                currentWeekDayWorkersIndex++
                currentDayOfWeek = DayOfWeek.nextDayOfWeek(currentDayOfWeek)
                EmergencyDay(day, dayOfWeek, weekDayWorker)
            }
        }
    }
}