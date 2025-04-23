package scope.commerce.product.api.dto

data class RankedProductServiceDto (
    val productId: Long,
    val name: String,
    val price: Long,
    val soldCount: Int
)

