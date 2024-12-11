package oncall

class OnCallController {

    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val (monthNumber, dayOfWeekUseName) = retryInput { inputView.readMonthAndDayOfWeek() }
        val month = convertMonth(monthNumber)
        val startDayOfWeek = convertDayOfWeek(dayOfWeekUseName)
        val workerService = retryInput { WorkerService(inputView.readWeekDayWorkers(), inputView.readHolidayWorkers()) }
        val workScheduleService = WorkScheduleService(month, startDayOfWeek)
        val emergencyDays = workScheduleService.getEmergencyDays(month, workerService)
        outputView.printEmergencySchedule(month, emergencyDays)
    }

    private fun convertMonth(monthNumber: Int): Month {
        val month = Month.entries.find { it.number == monthNumber }
        return requireNotNull(month) { Error.MONTH.getMessage() }
    }

    private fun convertDayOfWeek(dayOfWeekUseName: String): DayOfWeek {
        val dayOfWeek = DayOfWeek.entries.find { it.useName == dayOfWeekUseName }
        return requireNotNull(dayOfWeek) { Error.DAY_OF_WEEK.getMessage() }
    }
}