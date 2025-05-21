package scope.commerce.product.domain.model

data class Stock (
    val productId: Long,
    val userId: Long,
    val price: Long,
    val purchaseNumber: String? = null, // 고유한 구매번호 (Redis Set의 값)
)