package scope.commerce.application.order.dto

import java.time.LocalDateTime

data class OrderServiceDto(
    val orderId: Long,
    val totalAmount: Int,
    val discountAmount: Int,
    val finalAmount: Int,
    val orderedAt: LocalDateTime
)