package scope.commerce.user.application.dto.response

import scope.commerce.common.type.coupon.CouponDiscountType
import java.time.LocalDate

data class UserCouponQueryResponse(
    override val couponId: Long,
    override val name: String,
    override val discountType: CouponDiscountType,
    override val discountValue: Long,
    override val maxDiscountAmount: Long,
    override val maxQuantity: Long,
    override val remainingQuantity: Long,
    override val expiryAt: LocalDate,
    override val issuedAt: LocalDate
) : UserCouponResponse
