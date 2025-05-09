package scope.commerce.user.application.dto.response

import java.time.LocalDate

interface UserCouponResponse {
    val couponId: Long
    val name: String
    val discountType: String
    val discountValue: Long
    val maxDiscountAmount: Long
    val maxQuantity: Long
    val remainingQuantity: Long
    val expiryAt: LocalDate
    val issuedAt: LocalDate
}