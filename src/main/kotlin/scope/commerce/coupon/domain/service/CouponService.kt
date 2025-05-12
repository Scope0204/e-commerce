package scope.commerce.coupon.domain.service;

import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.coupon.domain.repository.CouponRepository

@Service
class CouponService(
    private val couponRepository: CouponRepository
) {
    /**
     * 쿠폰 유효성 검증 및 재고 차감
     */
    fun validateAndDecreaseCoupon(couponId: Long): Coupon {
        val coupon = validateCoupon(couponId) // 유효성 체크
        return decreaseCoupon(coupon) // 쿠폰 재고 차감
    }

    /**
     * 쿠폰 조회 및 검증
     * - 발급하려는 쿠폰이 유효한지 확인
     * - 쿠폰 재고 확인
     * - 확인 후 Coupon 객체를 리턴
     */
    @Transactional(readOnly = true)
    fun validateCoupon(couponId: Long): Coupon {
        val coupon = couponRepository.findById(couponId)
        coupon.validateForIssue()
        return coupon
    }

    /**
     * 쿠폰 재고 차감
     */
    @Transactional
    fun decreaseCoupon(coupon: Coupon): Coupon {
        val decreasedCoupon = coupon.decrease(1)
        couponRepository.save(decreasedCoupon)
        return decreasedCoupon
    }

    /**
     * ID 리스트로 쿠폰 도메인 정보 조회
     */
    @Transactional(readOnly = true)
    fun getCouponsInfo(couponIds: List<Long>): Map<Long, Coupon> {
        return couponRepository.findByIdIn(couponIds)
            .associateBy { it.id }
    }

    fun issueCoupon(coupon: Coupon) {
        TODO("쿠폰 타입별 도메인 발급 로직이 필요한 경우 여기에 구현")
    }
}
