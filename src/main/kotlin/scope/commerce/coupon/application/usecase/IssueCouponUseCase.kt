package scope.commerce.coupon.application.usecase

import org.springframework.stereotype.Service
import scope.commerce.coupon.application.dto.command.IssueCouponCommand
import scope.commerce.coupon.application.dto.response.IssueCouponResponse
import scope.commerce.coupon.domain.service.CouponService
import scope.commerce.user.domain.service.UserCouponService
import java.time.LocalDate

@Service
class IssueCouponUseCase(
    private val couponService: CouponService,
    private val userCouponService: UserCouponService
) {
    fun issueCoupon(command: IssueCouponCommand): IssueCouponResponse {
        // 1. 유저가 이미 발급받았는지 검증
        if (userCouponService.hasIssued(command.userId, command.couponId)) {
            throw IllegalStateException("이미 발급받은 쿠폰입니다.")
        }
        // 2. 쿠폰 유효성 검증 + 재고 차감 (중복 조회 방지)
        val coupon = couponService.validateAndDecreaseCoupon(command.couponId)

        // 3. 유저 쿠폰 발급 이력 저장
        val userCoupon = userCouponService.issueUserCoupon(command.userId, command.couponId)

        // 발급받은 쿠폰 정보 반환
        return IssueCouponResponse(
            couponId = coupon.id,
            name = coupon.name,
            discountType = coupon.discountType,
            discountValue = coupon.discountValue,
            maxDiscountAmount = coupon.maxDiscountAmount,
            maxQuantity = coupon.maxQuantity,
            remainingQuantity = coupon.remainingQuantity,
            expiryAt = coupon.expiryAt,
            issuedAt = userCoupon.issuedAt,
        )
    }
}