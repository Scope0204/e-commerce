package scope.commerce.order.application.dto.response

import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long ?= null,
    val userCouponId: Long ?= null,
    val totalAmount: Long,
    val discountAmount: Long,
    val finalAmount: Long,
    val orderedAt: LocalDateTime
)