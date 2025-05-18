package scope.commerce.point.domain.service

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import scope.commerce.point.domain.model.Point
import scope.commerce.point.domain.repository.PointRepository
import kotlin.test.assertEquals

class PointServiceTest {
    @Mock
    private lateinit var pointRepository: PointRepository

    @InjectMocks
    private lateinit var pointService: PointService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    // ========== SUCCESS ==========

    @Test
    fun `사용자 포인트 조회 테스트`() {
        // Given
        val id = 1L
        val userId = 1L
        val existing = Point(id = id, userId = userId, amount = 1000)

        `when`(pointRepository.findByUserId(userId)).thenReturn(existing)

        // When
        val result = pointService.getUserPoint(userId)

        // Then
        assertEquals(1000, result.amount)
    }

    @Test
    fun `기존 포인트가 있는 사용자의 포인트 충전 테스트`() {
        // Given
        val id = 1L
        val userId = 2L
        val existing = Point(id = id, userId = userId, amount = 1000)
        val expected = Point(id = id, userId = userId, amount = 3000)

        `when`(pointRepository.findByUserId(userId)).thenReturn(existing)
        `when`(pointRepository.save(expected)).thenReturn(expected)

        // When
        val result = pointService.charge(userId, 2000)

        // Then
        assertEquals(3000, result.amount)
        verify(pointRepository).save(expected)
    }

    @Test
    fun `기존 포인트가 없을 경우 신규 생성 후 충전되는 테스트`() {
        // Given
        val id = 1L
        val userId = 3L
        val existing = Point(id = id, userId = userId, amount = 0)
        val expected = Point(id = id, userId = userId, amount = 2000)

        `when`(pointRepository.findByUserId(userId)).thenReturn(existing)
        `when`(pointRepository.save(expected)).thenReturn(expected)

        // When
        val result = pointService.charge(userId, 2000)

        // Then
        assertEquals(2000, result.amount)
        verify(pointRepository).save(expected)
    }

    // ========== EXCEPTION ==========
    @Test
    fun `존재하지 않는 사용자 포인트 조회시 예외 발생`() {
        // Given
        val userId = 999L
        `when`(pointRepository.findByUserId(userId)).thenReturn(null)

        // When & Then
        assertThrows(IllegalArgumentException::class.java) {
            pointService.getUserPoint(userId)
        }
    }

    @Test
    fun `존재하지 않는 사용자에게 포인트 충전 시 예외 발생`() {
        // Given
        val userId = 999L
        val amount = 1000L
        `when`(pointRepository.findByUserId(userId)).thenReturn(null)

        // When & Then
        assertThrows(IllegalArgumentException::class.java) {
            pointService.charge(userId, amount)
        }
    }
    @Test
    fun `충전 금액이 1 미만일 경우 예외 발생`() {
        // Given
        val id = 1L
        val userId = 1L
        val point = Point(id = id, userId = userId, amount = 5000)
        val chargeAmount = 0L
        `when`(pointRepository.findByUserId(userId)).thenReturn(point)

        // When & Then
        assertThrows(IllegalArgumentException::class.java) {
            pointService.charge(userId, chargeAmount)
        }
    }


}