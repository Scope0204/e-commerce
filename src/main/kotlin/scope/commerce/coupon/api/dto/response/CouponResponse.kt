package scope.commerce.coupon.api.dto.response

import scope.commerce.coupon.api.dto.CouponServiceDto
import java.time.LocalDate

class CouponResponse {
    data class Coupon(
        val couponId: Long,
        val name: String,
        val discountType: String,
        val discountValue: Long,
        val maxDiscountAmount: Long,
        val maxQuantity: Int,
        val remainingQuantity: Int,
        val expiryAt: LocalDate,
        val issuedAt: LocalDate
    ) {
        companion object {
            fun from(serviceDto: CouponServiceDto): Coupon =
                Coupon(
                    couponId = serviceDto.couponId,
                    name = serviceDto.name,
                    discountType = serviceDto.discountType,
                    discountValue = serviceDto.discountValue,
                    maxDiscountAmount = serviceDto.maxDiscountAmount,
                    maxQuantity = serviceDto.maxQuantity,
                    remainingQuantity = serviceDto.remainingQuantity,
                    expiryAt = serviceDto.expiryAt,
                    issuedAt = serviceDto.issuedAt
                )
        }
    }
}