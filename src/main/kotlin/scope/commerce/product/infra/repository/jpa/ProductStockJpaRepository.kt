package scope.commerce.product.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.product.infra.entity.ProductStockEntity

interface ProductStockJpaRepository : JpaRepository<ProductStockEntity, Long> {
    fun findByProductEntityId(productId: Long): ProductStockEntity?
    fun findByProductEntityIdIn(productIds: List<Long>): List<ProductStockEntity>
}