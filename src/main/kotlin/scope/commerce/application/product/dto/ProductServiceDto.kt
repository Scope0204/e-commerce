package scope.commerce.application.product.dto

data class ProductServiceDto(
    val productId: Long,
    val name: String,
    val price: Long,
    val quantity: Int,
)