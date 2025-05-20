package scope.commerce.product.domain.model

import scope.commerce.common.type.product.StockHistoryType

data class StockHistory (
    val productId: Long,
    val orderId: Long,
    val userId: Long,
    val purchaseNumber: String, // 고유한 구매번호 (Redis Set의 값)
    val price: Long,
    val type: StockHistoryType
)