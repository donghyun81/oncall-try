package oncall

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WorkerServiceTest {

    private lateinit var workerService: WorkerService

    @BeforeEach
    fun setUp() {
        val weekdayWorkers = listOf("준팍", "도밥", "고니", "수아", "루루", "글로")
        val holidayWorkers = listOf("수아", "글로", "고니", "도밥", "준팍")
        workerService = WorkerService(weekdayWorkers, holidayWorkers)
    }

    @Test
    fun `평일 근무자 반환 테스트`() {
        val weekdayWorkers = listOf("준팍", "도밥", "고니", "수아", "루루", "글로", "준팍")
        weekdayWorkers.forEach { worker ->
            assertEquals(workerService.getWorker(false), worker)
        }
    }

    @Test
    fun `주말 근무자 반환 테스트`() {
        val weekdayWorkers = listOf("수아", "글로", "고니", "도밥", "준팍", "수아")
        weekdayWorkers.forEach { worker ->
            assertEquals(workerService.getWorker(true), worker)
        }
    }
}