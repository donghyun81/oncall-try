package oncall

import java.util.LinkedList

class WorkerService(
    private val weekDayWorkers: List<String>,
    private val holidayWorkers: List<String>
) {

    private val weekDayWorkersQueue = LinkedList(weekDayWorkers)
    private val holidayWorkersQueue = LinkedList(holidayWorkers)

    fun getWorker(isDayOff: Boolean): String {
        if (isDayOff) {
            val worker = holidayWorkersQueue.poll()
            holidayWorkersQueue.add(worker)
            return worker
        }
        val worker = weekDayWorkersQueue.poll()
        weekDayWorkersQueue.add(worker)
        return worker
    }
}