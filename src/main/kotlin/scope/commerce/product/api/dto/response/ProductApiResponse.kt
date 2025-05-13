package scope.commerce.product.api.dto.response

class ProductApiResponse {
    data class Product (
        val productId: Long,
        val name: String,
        val price: Long,
        val quantity: Long,
    )

    data class RankedProduct(
        val productId: Long,
        val name: String,
        val price: Long,
        val soldCount: Long
    )
}