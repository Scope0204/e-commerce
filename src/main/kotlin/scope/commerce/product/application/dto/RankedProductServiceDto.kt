package scope.commerce.product.application.dto

data class RankedProductServiceDto (
    val productId: Long,
    val name: String,
    val price: Long,
    val soldCount: Int
)

