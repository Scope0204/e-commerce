package scope.commerce.coupon.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.coupon.api.dto.response.CouponApiResponse
import scope.commerce.coupon.application.dto.response.IssueCouponResponse

@Component
class CouponResponseMapper {
    fun toApiResponse(response: IssueCouponResponse): CouponApiResponse.Coupon {
        return CouponApiResponse.Coupon(
            couponId = response.couponId,
            name = response.name,
            discountType = response.discountType,
            discountValue = response.discountValue,
            maxDiscountAmount = response.maxDiscountAmount,
            maxQuantity = response.maxQuantity,
            remainingQuantity = response.remainingQuantity,
            expiryAt = response.expiryAt,
            issuedAt = response.issuedAt
        )
    }
}