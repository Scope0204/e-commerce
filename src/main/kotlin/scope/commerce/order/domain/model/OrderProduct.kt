package scope.commerce.order.domain.model

class OrderProduct(
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long,
    val bucketProductId: Long? = null
)
