package scope.commerce.coupon.api.dto.response

import scope.commerce.common.type.coupon.CouponDiscountType
import java.time.LocalDate

class CouponApiResponse {
    data class Coupon(
        val couponId: Long,
        val name: String,
        val discountType: CouponDiscountType,
        val discountValue: Long,
        val maxDiscountAmount: Long,
        val maxQuantity: Long,
        val remainingQuantity: Long,
        val expiryAt: LocalDate,
        val issuedAt: LocalDate
    )
}