package scope.commerce.api.order.dto.response

import scope.commerce.application.order.dto.OrderServiceDto
import java.time.LocalDateTime

class OrderResponse {
    data class Summary(
        val orderId: Long,
        val totalAmount: Int,
        val discountAmount: Int,
        val finalAmount: Int,
        val orderedAt: LocalDateTime
    ) {
        companion object {
            fun from(serviceDto: OrderServiceDto): Summary =
                Summary(
                    orderId = serviceDto.orderId,
                    totalAmount = serviceDto.totalAmount,
                    discountAmount = serviceDto.discountAmount,
                    finalAmount = serviceDto.finalAmount,
                    orderedAt = serviceDto.orderedAt
                )
        }
    }
}
