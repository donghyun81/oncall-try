package oncall

import camp.nextstep.edu.missionutils.Console

class InputView {

    fun readEmergencyWorkDays(): String {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요>")
        return Console.readLine()
    }

    fun readWeekdayWorker(): String {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        return Console.readLine()
    }

    fun readHolidayWorker(): String {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
        return Console.readLine()
    }
}