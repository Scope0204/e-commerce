package scope.commerce.api.order.dto.request

class OrderRequest {
    data class Create(
        val userId: Long,
        val fromBucket: Boolean,
        val couponId: Long?,
        val products: List<Product>
    ) {
        data class Product(
            val productId: Long,
            val quantity: Int
        )
    }
}
