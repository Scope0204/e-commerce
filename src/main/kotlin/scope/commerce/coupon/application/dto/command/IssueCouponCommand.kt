package scope.commerce.coupon.application.dto.command

data class IssueCouponCommand(
    val userId: Long,
    val couponId: Long
)
