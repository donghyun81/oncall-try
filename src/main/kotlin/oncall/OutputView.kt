package oncall

class OutputView {
    fun printEmergencySchedule(month: Month, emergencyDays: List<EmergencyDay>) {
        for (emergencyDay in emergencyDays) {
            if (emergencyDay.isPublicHoliday) {
                println("${month.number}월 ${emergencyDay.day}일 ${emergencyDay.dayOfWeek}(휴일) ${emergencyDay.worker}")
                continue
            }
            println("${month.number}월 ${emergencyDay.day}일 ${emergencyDay.dayOfWeek} ${emergencyDay.worker}")
        }
    }
}