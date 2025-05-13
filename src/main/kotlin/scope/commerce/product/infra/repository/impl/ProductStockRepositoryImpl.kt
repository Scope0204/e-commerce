package scope.commerce.product.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.domain.repository.ProductStockRepository
import scope.commerce.product.infra.mapper.ProductMapper
import scope.commerce.product.infra.mapper.ProductStockMapper
import scope.commerce.product.infra.repository.jpa.ProductJpaRepository
import scope.commerce.product.infra.repository.jpa.ProductStockJpaRepository

@Repository
class ProductStockRepositoryImpl(
    private val productMapper: ProductMapper,
    private val productStockMapper: ProductStockMapper,
    private val productJpaRepository: ProductJpaRepository,
    private val productStockJpaRepository: ProductStockJpaRepository
) : ProductStockRepository {

    override fun findByProductId(productId: Long): ProductStock {
        val productStockEntity = productStockJpaRepository.findByProductEntityId(productId)
            ?: throw IllegalArgumentException("상품재고가 존재하지 않습니다. productId=$productId")

        return productStockMapper.toProductStock(productStockEntity)
    }

    override fun findByProductIds(productIds: List<Long>): List<ProductStock> {
        return productStockJpaRepository.findByProductEntityIdIn(productIds)
            .map { productStockMapper.toProductStock(it) }
    }

    override fun save(productStock: ProductStock) {
        val productEntity = productJpaRepository.getReferenceById(productStock.productId)
        val entity = productStockMapper.toProductStockEntity(productStock, productEntity)
        productStockJpaRepository.save(entity)
    }
}