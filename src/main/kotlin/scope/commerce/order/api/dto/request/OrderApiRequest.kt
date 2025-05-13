package scope.commerce.order.api.dto.request

class OrderApiRequest {
    data class Create(
        val userId: Long,
        val fromBucket: Boolean,
        val couponId: Long?,
        val products: List<Product>
    ) {
        data class Product(
            val productId: Long,
            val quantity: Long
        )
    }
}
