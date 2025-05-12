package scope.commerce.order.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.coupon.api.dto.response.CouponApiResponse
import scope.commerce.coupon.application.dto.response.IssueCouponResponse
import scope.commerce.order.api.dto.response.OrderApiResponse
import scope.commerce.order.application.dto.response.OrderResponse
import java.time.LocalDateTime

@Component
class OrderResponseMapper {
    fun toApiResponse(response: OrderResponse): OrderApiResponse.Summary {
        return OrderApiResponse.Summary(
            orderId = response.orderId,
            userCouponId = response.userCouponId,
            totalAmount = response.totalAmount,
            discountAmount = response.discountAmount,
            finalAmount = response.finalAmount,
            orderedAt = response.orderedAt
        )
    }
}