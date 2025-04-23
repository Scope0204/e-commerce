package scope.commerce.product.api.dto

data class ProductServiceDto(
    val productId: Long,
    val name: String,
    val price: Long,
    val quantity: Int,
)