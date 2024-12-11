package oncall

class OnCallController {

    private val inputView = InputView()

    fun run() {
        val (month, startDayOfWeek) = inputView.readMonthAndDayOfWeek()
        val weekDayWorkers = inputView.readWeekDayWorkers()
        val holidayWorkers = inputView.readHolidayWorkers()
    }
}