package scope.commerce.product.domain.repository

import scope.commerce.product.domain.model.ProductStock

interface ProductStockRepository {
    fun findByProductId(productId: Long): ProductStock
    fun findByProductIds(productIds: List<Long>): List<ProductStock>
    fun save(productStock: ProductStock)
}
