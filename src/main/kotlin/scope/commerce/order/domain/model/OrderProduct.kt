package scope.commerce.order.domain.model

import scope.commerce.product.domain.model.Product

class OrderProduct(
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long,
    val bucketProductId: Long? = null
) {
    companion object {
        fun create(product: Product): OrderProduct {
            requireNotNull(product.id) { "상품 ID가 없습니다." }
            return OrderProduct(
                productId = product.id,
                quantity = product.quantity,
                unitPrice = product.price
            )
        }
    }
}
