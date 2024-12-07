package oncall

class OutputView {
    fun printResult(workSchedule: WorkSchedule) {
        workSchedule.emergencyDays.forEach { emergencyDay ->
            if (emergencyDay.isPublicDay) println("${workSchedule.month}월 ${emergencyDay.day}일 ${emergencyDay.dayOfWeek.useName}(휴일) ${emergencyDay.workerName}")
            else println("${workSchedule.month}월 ${emergencyDay.day}일 ${emergencyDay.dayOfWeek.useName} ${emergencyDay.workerName}")
        }
    }
}