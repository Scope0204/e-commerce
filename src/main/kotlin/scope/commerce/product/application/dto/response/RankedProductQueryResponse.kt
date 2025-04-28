package scope.commerce.product.application.dto.response

data class RankedProductQueryResponse (
    override val productId: Long,
    override val name: String,
    override val price: Long,
    override val quantity: Long,
    val soldCount: Long
) : ProductResponse

