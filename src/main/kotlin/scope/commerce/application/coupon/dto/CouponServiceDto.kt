package scope.commerce.application.coupon.dto

import java.time.LocalDate

data class CouponServiceDto(
    val couponId: Long,
    val name: String,
    val discountType: String,
    val discountValue: Long,
    val maxDiscountAmount: Long,
    val maxQuantity: Int,
    val remainingQuantity: Int,
    val expiryAt: LocalDate,
    val issuedAt: LocalDate
)
