package oncall.domain.service

import oncall.common.Error
import oncall.common.NEXT_INDEX
import java.util.LinkedList

class WorkerService(
    initWeekDayWorkers: List<String>,
    initHolidayWorkers: List<String>
) {

    private val weekDayWorkersQueue = LinkedList(initWeekDayWorkers)
    private val holidayWorkersQueue = LinkedList(initHolidayWorkers)
    private var currentWorker = INIT_WORKER

    init {
        initWorkers(initWeekDayWorkers)
        initWorkers(initHolidayWorkers)
    }

    private fun initWorkers(initWorkers: List<String>) {
        require(initWorkers.size == initWorkers.distinct().size) { Error.DUPLICATE.getMessage() }
        require(initWorkers.size in MIN_WORKERS_SIZE..MAX_WORKERS_SIZE) { Error.WORKERS_COUNT.getMessage() }
        initWorkers.forEach { require(it.length <= MIN_WORKER_NAME_LENGTH) { Error.WORKER_NAME_LENGTH.getMessage() } }
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
            val worker = workers.removeAt(NEXT_INDEX)
            currentWorker = worker
            return worker
        }
        val worker = workers.poll()
        currentWorker = worker
        return worker
    }

    companion object {
        private const val MIN_WORKERS_SIZE = 5
        private const val MAX_WORKERS_SIZE = 35
        private const val MIN_WORKER_NAME_LENGTH = 5
        private const val INIT_WORKER = ""
    }
}