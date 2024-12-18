package oncall.view

import oncall.common.Month
import oncall.model.EmergencyDay

class OutputView {
    fun printEmergencySchedule(month: Month, emergencyDays: List<EmergencyDay>) {
        for (emergencyDay in emergencyDays) {
            if (emergencyDay.isPublicHoliday) {
                println("${month.number}월 ${emergencyDay.day}일 ${emergencyDay.dayOfWeek.useName}(휴일) ${emergencyDay.worker}")
                continue
            }
            println("${month.number}월 ${emergencyDay.day}일 ${emergencyDay.dayOfWeek.useName} ${emergencyDay.worker}")
        }
    }

    fun printError(message: String?) {
        println(message)
    }
}