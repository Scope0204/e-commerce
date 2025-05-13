package scope.commerce.product.application.dto.response

data class ProductListQueryResponse(
    val products: List<ProductResponse>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int
)