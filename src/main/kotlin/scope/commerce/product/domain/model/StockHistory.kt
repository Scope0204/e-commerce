package scope.commerce.product.domain.model

import scope.commerce.common.type.product.StockHistoryType
import scope.commerce.common.type.product.StockHistoryType.PLUS
import scope.commerce.user.domain.model.User

data class StockHistory (
    val productId: Long,
    val userId: Long,
    val purchaseNumber: String, // 고유한 구매번호 (Redis Set의 값)
    val price: Long,
    val type: StockHistoryType
) {
    companion object {
        fun create(product: Product, user: User, purchaseNumber: String): StockHistory {
            requireNotNull(product.id) { "상품 ID가 없습니다." }
            requireNotNull(user.id) { "유저 ID가 없습니다." }
            return StockHistory(
                productId = product.id,
                userId = user.id,
                purchaseNumber = purchaseNumber,
                price = product.price,
                type = PLUS
            )
        }
    }
}
