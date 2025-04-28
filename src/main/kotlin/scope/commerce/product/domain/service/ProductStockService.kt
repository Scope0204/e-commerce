package scope.commerce.product.domain.service

import org.springframework.stereotype.Service
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.domain.repository.ProductStockRepository

@Service
class ProductStockService(
    private val productStockRepository: ProductStockRepository
) {
    fun getStockByProductId(productId: Long): ProductStock {
        return productStockRepository.findByProductId(productId)
    }
}