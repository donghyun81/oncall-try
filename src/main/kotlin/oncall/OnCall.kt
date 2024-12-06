package oncall

class OnCall {
    private val inputView = InputView()

    fun run() {
        val (month, startDayOfWeek) = inputView.readOnCallMonthAndDayOfWeek()
        val weekDayWorkers = inputView.readWeekDayWorkers()
        val holidayWorkers = inputView.readHolidayWorkers()
    }
}