package scope.commerce.product.application.dto

data class ProductServiceDto(
    val productId: Long,
    val name: String,
    val price: Long,
    val quantity: Int,
)