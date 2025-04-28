package scope.commerce.product.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.domain.repository.ProductStockRepository
import scope.commerce.product.infra.mapper.ProductStockMapper
import scope.commerce.product.infra.repository.jpa.ProductStockJpaRepository

@Repository
class ProductStockRepositoryImpl(
    private val productStockMapper: ProductStockMapper,
    private val productStockJpaRepository: ProductStockJpaRepository
) : ProductStockRepository {

    override fun findByProductId(productId: Long): ProductStock {
        val productStockEntity = productStockJpaRepository.findByProductEntityId(productId)
            ?: throw IllegalArgumentException("상품재고가 존재하지 않습니다. productId=$productId")

        return productStockMapper.toProductStock(productStockEntity)
    }
}