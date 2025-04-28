package scope.commerce.product.domain.repository

import scope.commerce.product.domain.model.Product

interface ProductRepository {
    fun findById(productId: Long): Product
}
