package scope.commerce.common.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Redis INCR 명령어를 통해 원자적이고 전역적으로 중복 없는 주문 번호 생성
 * - 생성 예시 : G2025052000001
 */
@Component
class PurchaseNumberGenerator(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val keyPrefix = "purchase:sequence:"

    fun generate(): String {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val key = "$keyPrefix$date"

        // Redis의 INCR은 key가 없으면 1부터 시작
        val seq = redisTemplate.opsForValue().increment(key) ?: throw IllegalStateException("Redis increment failed")

        // G + 날짜 + 5자리 시퀀스 (zero-padding)
        return "G${date}${seq.toString().padStart(5, '0')}" // 예: G2025052000001
    }
}
