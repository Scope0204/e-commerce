package scope.commerce.order.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.coupon.api.dto.request.CouponApiRequest
import scope.commerce.coupon.application.dto.command.IssueCouponCommand
import scope.commerce.order.api.dto.request.OrderApiRequest
import scope.commerce.order.application.dto.command.OrderCommand

@Component
class OrderRequestMapper {
    fun toOrderCommand(request: OrderApiRequest.Create): OrderCommand {
        return OrderCommand(
            userId = request.userId,
            fromBucket = request.fromBucket,
            couponId = request.couponId,
            products = request.products
        )
    }
}