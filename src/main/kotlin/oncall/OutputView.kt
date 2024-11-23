package oncall

class OutputView {

    fun printResult(emergencyMonth: EmergencyMonth) {
        emergencyMonth.emergencyDays.forEach { day ->
            println("${emergencyMonth.month.month}월 ${day.date}일 ${day.dayOfWeek.text} ${day.worker.name}")
        }
    }
}