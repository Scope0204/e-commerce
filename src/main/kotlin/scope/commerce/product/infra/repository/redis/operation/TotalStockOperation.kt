package scope.commerce.product.infra.repository.redis.operation

import mu.KotlinLogging
import org.springframework.data.redis.core.RedisOperations
import scope.commerce.product.domain.model.Stock

/**
 * 전체 재고 사용량 체크 레디스 오퍼레이션
 * 자료구조 : Set
 */
class TotalStockOperation {
    companion object {
        private val log = KotlinLogging.logger { }
        private var key = "stock:total"

        /**
         * SADD Operation
         */
        fun add(redisOperations: RedisOperations<String, String>, stock: Stock) {
            val key = getKey("$")
            redisOperations.opsForSet().add(key, stock.purchaseNumber)
            log.info { "Added stock usage for $key : ${stock.purchaseNumber}" }
        }

        /**
         * SREM Operation
         */
        fun remove(redisOperations: RedisOperations<String, String>, stock: Stock): Long? {
            val key = getKey("$")
            val popCnt = redisOperations.opsForSet().remove(key, stock.purchaseNumber)
            log.info { "Removed stock usage for $key : ${stock.purchaseNumber}" }
            return popCnt
        }

        /**
         * SCARD Operation
         */
        fun totalUsedCount(redisOperations: RedisOperations<String, String>, stock: Stock): Long {
            val key = getKey("$")
            val size = redisOperations.opsForSet().size(key) ?: 0
            return size
        }

        fun getKey(keyPrefix: String) = "$keyPrefix:$key"
    }
}