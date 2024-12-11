package oncall

enum class Error(private val message: String) {
    FORMAT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    MONTH("월은 1~12까지의 정수만 가능합니다. 다시 입력해 주세요."),
    DAY_OF_WEEK("요일은 월,화,수,목,금,토,일만 입력가능합니다. 다시 입력해 주세요."),
    DUPLICATE("중복되는 이름이 있습니다. 다시 입력해 주세요."),
    WORKERS_COUNT("근무자는 최소 5명에서 최대 35명까지 입니다. 다시 입력해 주세요."),
    WORKER_NAME_LENGTH("근무자 이름의 길이는 최대 5글자 입니다. 다시 입력해 주세요.");

    fun getMessage() = "[ERROR] " + this.message
}