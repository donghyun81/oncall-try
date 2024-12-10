package oncall

import java.util.*

class WorkerManager(private val weekDayWorker: List<String>, private val holidayWorker: List<String>) {

    private val weekDayWorkersQueue = LinkedList(weekDayWorker)
    private val holidayWorkersQueue = LinkedList(holidayWorker)
    private var previousName: String = ""

    fun getCurrentWorkerName(isHolidayWorker: Boolean): String {
        val currentWorkerName = if (isHolidayWorker) extractHolyDayWorker()
        else extractWeekDayWorker()
        previousName = currentWorkerName
        return currentWorkerName
    }

    private fun extractWeekDayWorker(): String {
        if (weekDayWorkersQueue.size == 2) weekDayWorkersQueue.addAll(weekDayWorkersQueue)
        if (weekDayWorkersQueue.peek() == previousName) return weekDayWorkersQueue.removeAt(1)
        return weekDayWorkersQueue.poll()
    }

    private fun extractHolyDayWorker(): String {
        if (holidayWorkersQueue.size == 2) holidayWorkersQueue.addAll(holidayWorker)
        if (holidayWorkersQueue.peek() == previousName) return holidayWorkersQueue.removeAt(1)
        return holidayWorkersQueue.poll()
    }
}