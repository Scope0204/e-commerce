package scope.commerce.product.infra.repository.impl

import org.springframework.data.redis.core.RedisOperations
import org.springframework.stereotype.Repository
import scope.commerce.product.domain.model.Stock
import scope.commerce.product.domain.repository.StockUsageRepository
import scope.commerce.product.infra.repository.redis.RedisTransaction
import scope.commerce.product.infra.repository.redis.operation.TotalStockOperation

@Repository
class StockUsageRedisImpl (
    private val redisTemplate: RedisOperations<String, String>
) : StockUsageRepository {

    override fun getTotalUsedCount(stock: Stock): Long {
        return TotalStockOperation.totalUsedCount(redisTemplate, stock)
    }

    override fun addUsage(stock: Stock) {
        RedisTransaction.transaction(redisTemplate) { ops ->
            TotalStockOperation.add(ops, stock)
        }
    }
}