package oncall.utils

fun <T> retryInput(printErrorMessage: (String?) -> Unit, runInput: () -> T): T {
    while (true) {
        try {
            return runInput()
        } catch (e: IllegalArgumentException) {
            printErrorMessage(e.message)
        }
    }
}