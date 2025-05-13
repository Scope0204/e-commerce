package scope.commerce.product.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.order.domain.model.OrderProduct
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.domain.repository.ProductStockRepository

@Service
class ProductStockService(
    private val productStockRepository: ProductStockRepository
) {
    fun getStockByProductId(productId: Long): ProductStock {
        return productStockRepository.findByProductId(productId)
    }

    fun getStocksByProductIds(productIds: List<Long>): List<ProductStock> {
        return productStockRepository.findByProductIds(productIds)
    }

    @Transactional
    fun decrease(orderProducts: List<OrderProduct>) {
        for (orderProduct in orderProducts) {
            val productId = orderProduct.productId
            val quantity = orderProduct.quantity
            val productStock = productStockRepository.findByProductId(productId)

            productStock.validateEnough(quantity)

            val decreasedStock = productStock.decrease(quantity)
            productStockRepository.save(decreasedStock)
        }
    }
}