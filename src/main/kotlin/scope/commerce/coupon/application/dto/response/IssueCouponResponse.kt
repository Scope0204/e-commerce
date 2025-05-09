package scope.commerce.coupon.application.dto.response

import java.time.LocalDate

data class IssueCouponResponse(
    val couponId: Long,
    val name: String,
    val discountType: String,
    val discountValue: Long,
    val maxDiscountAmount: Long,
    val maxQuantity: Long,
    val remainingQuantity: Long,
    val expiryAt: LocalDate,
    val issuedAt: LocalDate
)
