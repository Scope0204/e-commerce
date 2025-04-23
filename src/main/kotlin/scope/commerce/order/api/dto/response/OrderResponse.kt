package scope.commerce.order.api.dto.response

import scope.commerce.order.api.dto.OrderServiceDto
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
