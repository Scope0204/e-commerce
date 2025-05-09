package scope.commerce.product.application.dto.response

interface ProductResponse {
    val productId: Long
    val name: String
    val price: Long
    val quantity: Long
}