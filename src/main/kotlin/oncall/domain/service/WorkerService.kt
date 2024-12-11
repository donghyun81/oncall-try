package oncall.domain.service

import oncall.common.Error
import java.util.LinkedList

class WorkerService(
    initWeekDayWorkers: List<String>,
    initHolidayWorkers: List<String>
) {

    private val weekDayWorkersQueue = LinkedList(initWeekDayWorkers)
    private val holidayWorkersQueue = LinkedList(initHolidayWorkers)
    private var currentWorker = ""

    init {
        initWorkers(initWeekDayWorkers)
        initWorkers(initHolidayWorkers)
    }

    private fun initWorkers(initWorkers: List<String>) {
        require(initWorkers.size == initWorkers.distinct().size) { Error.DUPLICATE.getMessage() }
        require(initWorkers.size in 5..35) { Error.WORKERS_COUNT.getMessage() }
        initWorkers.forEach { require(it.length <= 5) { Error.WORKER_NAME_LENGTH.getMessage() } }
    }

    fun getWorker(isDayOff: Boolean): String {
        if (isDayOff) {
            val worker = extractWorker(holidayWorkersQueue)
            holidayWorkersQueue.add(worker)
            return worker
        }
        val worker = extractWorker(weekDayWorkersQueue)
        weekDayWorkersQueue.add(worker)
        return worker
    }

    private fun extractWorker(workers: LinkedList<String>): String {
        if (currentWorker == workers.peek()) {
            val worker = workers.removeAt(1)
            currentWorker = worker
            return worker
        }
        val worker = workers.poll()
        currentWorker = worker
        return worker
    }
}