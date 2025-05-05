package scope.commerce.coupon.domain.service;

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
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
    @Transactional
    fun validateAndDecreaseCoupon(couponId: Long): Coupon {
        val coupon = getCouponForIssuance(couponId) // 유효성 체크
        return decreaseCoupon(coupon) // 쿠폰 재고 차감
    }

    /**
     * 쿠폰 발급을 위한 쿠폰 조회 및 검증
     * - 발급하려는 쿠폰이 유효한지 확인
     * - 쿠폰 재고 확인
     * 확인 후 Coupon 객체를 리턴
     */
    fun getCouponForIssuance(couponId: Long): Coupon {
        val coupon = couponRepository.findById(couponId)
        coupon.validateForIssue()
        return coupon
    }

    /**
     * 쿠폰 재고 차감
     */
    fun decreaseCoupon(coupon: Coupon): Coupon {
        val decreasedCoupon = coupon.decrease(1)
        couponRepository.save(decreasedCoupon)
        return decreasedCoupon
    }

    fun getCouponsForIssuance(page: Int, size: Int, couponIds: List<Long>): Page<Coupon> {
        TODO("유저 쿠폰 목록 조회 시 여기에 구현. 페이징 적용 필요.")
    }

    fun issueCoupon(coupon: Coupon) {
        TODO("쿠폰 타입별 도메인 발급 로직이 필요한 경우 여기에 구현")
    }
}
