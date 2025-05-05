package scope.commerce.coupon.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.coupon.api.dto.request.CouponApiRequest
import scope.commerce.coupon.application.dto.command.IssueCouponCommand

@Component
class CouponRequestMapper {
    fun toIssueCouponCommand(request: CouponApiRequest.Issue): IssueCouponCommand {
        return IssueCouponCommand(
            userId = request.userId,
            couponId = request.couponId,
        )
    }
}