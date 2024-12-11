package oncall

import java.util.LinkedList

class WorkerService(
    private val weekDayWorkers: List<String>,
    private val holidayWorkers: List<String>
) {

    private val weekDayWorkersQueue = LinkedList(weekDayWorkers)
    private val holidayWorkersQueue = LinkedList(holidayWorkers)
    private var currentWorker = ""

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