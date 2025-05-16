package scope.commerce.user.application.dto.response

import scope.commerce.common.type.coupon.CouponDiscountType
import java.time.LocalDate

interface UserCouponResponse {
    val couponId: Long
    val name: String
    val discountType: CouponDiscountType
    val discountValue: Long
    val maxDiscountAmount: Long
    val maxQuantity: Long
    val remainingQuantity: Long
    val expiryAt: LocalDate
    val issuedAt: LocalDate
}