package oncall

class OnCallController {

    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val (monthNumber, dayOfWeekUseName) = inputView.readMonthAndDayOfWeek()
        val month = convertMonth(monthNumber)
        val startDayOfWeek = convertDayOfWeek(dayOfWeekUseName)
        val weekDayWorkers = inputView.readWeekDayWorkers()
        val holidayWorkers = inputView.readHolidayWorkers()
        val workerService = WorkerService(weekDayWorkers, holidayWorkers)
        val workScheduleService = WorkScheduleService(month, startDayOfWeek)
        val emergencyDays = workScheduleService.getEmergencyDays(month, workerService)
        outputView.printEmergencySchedule(month, emergencyDays)
    }

    private fun convertMonth(monthNumber: Int): Month {
        val month = Month.entries.find { it.number == monthNumber }
        return requireNotNull(month) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
    }

    private fun convertDayOfWeek(dayOfWeekUseName: String): DayOfWeek {
        val dayOfWeek = DayOfWeek.entries.find { it.useName == dayOfWeekUseName }
        return requireNotNull(dayOfWeek) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
    }
}