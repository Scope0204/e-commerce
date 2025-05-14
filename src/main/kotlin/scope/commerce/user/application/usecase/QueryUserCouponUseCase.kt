package scope.commerce.user.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.user.application.dto.response.UserCouponListQueryResponse
import scope.commerce.coupon.domain.service.CouponService
import scope.commerce.user.application.dto.response.UserCouponQueryResponse
import scope.commerce.user.domain.service.UserCouponService

@Service
class QueryUserCouponUseCase(
    private val couponService: CouponService,
    private val userCouponService: UserCouponService
) {
    @Transactional(readOnly = true)
    fun getUserCoupons(userId: Long, page: Int, size: Int): UserCouponListQueryResponse {

        // 1.유저 쿠폰 목록 조회 ( 쿠폰 목록 반환 )
        val userCouponPage = userCouponService.getUserCoupons(userId, page, size)
        val userCoupons = userCouponPage.content

        // 2. 유저 쿠폰 id 목록으로 쿠폰 정보 조회
        val couponIds = userCoupons.map { it.couponId }
        val couponsInfoMap = couponService.getCouponsInfo(couponIds)
        val userCouponQueryResponses = userCoupons.map { userCoupon ->
            val coupon = couponsInfoMap[userCoupon.couponId]
                ?: throw IllegalStateException("쿠폰 정보가 존재하지 않습니다. id=${userCoupon.couponId}")

            UserCouponQueryResponse(
                couponId = coupon.id!!,
                name = coupon.name,
                discountType = coupon.discountType,
                discountValue = coupon.discountValue,
                maxDiscountAmount = coupon.maxDiscountAmount,
                maxQuantity = coupon.maxQuantity,
                remainingQuantity = coupon.remainingQuantity,
                expiryAt = coupon.expiryAt,
                issuedAt = userCoupon.issuedAt
            )
        }
        // 3. 응답 정보 반환
        return UserCouponListQueryResponse(
            userCoupons = userCouponQueryResponses,
            page = page,
            size = size,
            totalElements = userCouponPage.totalElements,
            totalPages = userCouponPage.totalPages
        )
    }
}
