package scope.commerce.product.application.dto.response

data class ProductQueryResponse(
    override val productId: Long,
    override val name: String,
    override val price: Long,
    override val quantity: Long
) : ProductResponse