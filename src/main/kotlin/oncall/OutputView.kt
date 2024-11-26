package oncall

class OutputView {

    fun printResult(emergencyMonth: EmergencyMonth) {
        for (day in emergencyMonth.emergencyDays) {
            if (day.isPublicDay) {
                println("${emergencyMonth.month.month}월 ${day.date}일 ${day.dayOfWeek.text}(휴일) ${day.worker.name}")
                continue
            }
            println("${emergencyMonth.month.month}월 ${day.date}일 ${day.dayOfWeek.text} ${day.worker.name}")
        }
    }
}