package scope.commerce.user.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.user.api.dto.response.UserCouponApiResponse
import scope.commerce.user.application.dto.response.UserCouponListQueryResponse
import scope.commerce.user.application.dto.response.UserCouponResponse

@Component
class UserCouponResponseMapper {
    fun toApiResponse(response: UserCouponResponse): UserCouponApiResponse.Coupon {
        return UserCouponApiResponse.Coupon(
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
    fun toApiResponse(response: UserCouponListQueryResponse): List<UserCouponApiResponse.Coupon> {
        return response.userCoupons.map { toApiResponse(it) }
    }

    fun toPagination(response: UserCouponListQueryResponse): ApiResponse.Pagination {
        return ApiResponse.Pagination(
            page = response.page,
            size = response.size,
            totalElements = response.totalElements,
            totalPages = response.totalPages.toDouble()
        )
    }
}