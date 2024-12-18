package oncall.controller

import oncall.common.DayOfWeek
import oncall.common.Error
import oncall.common.Guide
import oncall.common.Month
import oncall.domain.service.WorkScheduleService
import oncall.domain.service.WorkerService
import oncall.utils.retryInput
import oncall.view.InputView
import oncall.view.OutputView

class OnCallController {

    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val (monthNumber, dayOfWeekUseName) = retryInput(printErrorMessage = outputView::printError) { inputView.readMonthAndDayOfWeek() }
        val month = convertMonth(monthNumber)
        val startDayOfWeek = convertDayOfWeek(dayOfWeekUseName)
        val workerService = getWorkerService()
        val workScheduleService = WorkScheduleService(month, startDayOfWeek)
        val emergencyDays = workScheduleService.getEmergencyDays(month, workerService)
        outputView.printEmergencySchedule(workScheduleService.month, emergencyDays)
    }

    private fun convertMonth(monthNumber: Int): Month {
        val month = Month.entries.find { it.number == monthNumber }
        return requireNotNull(month) { Error.MONTH.getMessage() }
    }

    private fun convertDayOfWeek(dayOfWeekUseName: String): DayOfWeek {
        val dayOfWeek = DayOfWeek.entries.find { it.useName == dayOfWeekUseName }
        return requireNotNull(dayOfWeek) { Error.DAY_OF_WEEK.getMessage() }
    }

    private fun getWorkerService() = retryInput(printErrorMessage = outputView::printError) {
        WorkerService(
            inputView.readWorkers(Guide.READ_WEEKDAY_WORKERS.message),
            inputView.readWorkers(Guide.READ_HOLIDAY_WORKER.message)
        )
    }
}