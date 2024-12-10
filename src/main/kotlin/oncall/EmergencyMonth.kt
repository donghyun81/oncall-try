package oncall

class EmergencyMonth(val month: Month, emergencyDays: List<EmergencyDay>) {
    private val emergencyDays = emergencyDays.toMutableList()

    init {
        initEmergencyDays()
    }

    private fun initEmergencyDays() {
        var currentDay = emergencyDays.first()
        for (index in 1..<emergencyDays.size) {
            val emergencyDay = emergencyDays[index]
            if (currentDay.worker == emergencyDay.worker) {
                val switchNextWorker = emergencyDays.subList(index + 1, emergencyDays.size)
                    .find { emergencyDay.isHolidayWork() == it.isHolidayWork() }
                if (switchNextWorker == null) {
                    currentDay = emergencyDay
                    continue
                }
                val switchNextWorkerIndex =
                    emergencyDays.subList(index, emergencyDays.size).indexOf(switchNextWorker) + index
                emergencyDays[index] = emergencyDays[index].copy(worker = switchNextWorker.worker)
                emergencyDays[switchNextWorkerIndex] =
                    emergencyDays[switchNextWorkerIndex].copy(worker = emergencyDay.worker)
            }
            currentDay = emergencyDays[index]
        }
    }

    fun getEmergencyDays() = emergencyDays.toList()
}