package scope.commerce.order.api.dto.response

import java.time.LocalDateTime

class OrderApiResponse {
    data class Summary(
        val orderId: Long?,
        val userCouponId: Long?,
        val totalAmount: Long,
        val discountAmount: Long,
        val finalAmount: Long,
        val orderedAt: LocalDateTime
    )
}
