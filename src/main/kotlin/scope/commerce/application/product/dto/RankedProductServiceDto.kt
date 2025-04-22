package scope.commerce.application.product.dto

data class RankedProductServiceDto (
    val productId: Long,
    val name: String,
    val price: Long,
    val soldCount: Int
)

