package oncall

class OnCall {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val (monthNumber, dayOfWeek) = retryInput { inputView.readOnCallMonthAndDayOfWeek() }
        val workManager = retryInput {
            val weekDayWorker = retryInput { inputView.readWeekDayWorkers() }
            val holidayManager = retryInput { inputView.readHolidayWorkers() }
            WorkerManager(weekDayWorker, holidayManager)
        }
        val month = Month.convertMonth(monthNumber)
        val dayOfWeekManager = DayOfWeekManager(DayOfWeek.convertDayOfWeek(dayOfWeek))
        val workSchedule = getWorkSchedule(workManager, month, dayOfWeekManager)
        outputView.printResult(workSchedule)
    }

    private fun getWorkSchedule(
        workManager: WorkerManager,
        month: Month,
        dayOfWeekManager: DayOfWeekManager
    ): WorkSchedule {
        val emergencyDays = (month.startDay..month.endDay).map { day ->
            val isPublicHoliday = PublicHoliday.isPublicHoliday(month.number, day)
            val isHolidayWorker = dayOfWeekManager.isHoliday() || isPublicHoliday
            val workName = workManager.getCurrentWorkerName(isHolidayWorker)
            EmergencyDay(day, dayOfWeekManager.getCurrentDayOfWeek(), workName, isPublicHoliday)
        }
        return WorkSchedule(month.number, emergencyDays)
    }
}