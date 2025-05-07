package scope.commerce.user.api.dto.response

import java.time.LocalDate

class UserCouponApiResponse {
    data class Coupon(
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
}