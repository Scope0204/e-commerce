package scope.commerce.coupon.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.coupon.domain.repository.CouponRepository
import scope.commerce.coupon.infra.mapper.CouponMapper
import scope.commerce.coupon.infra.repository.jpa.CouponJpaRepository

@Repository
class CouponRepositoryImpl(
    private val couponMapper: CouponMapper,
    private val couponJpaRepository: CouponJpaRepository
) : CouponRepository {
    override fun findById(couponId: Long): Coupon {
        return couponMapper.toCoupon(
            couponJpaRepository.findById(couponId)
                .orElseThrow { IllegalArgumentException("쿠폰 정보를 찾을 수 없습니다. couponId=$couponId") }
        )
    }

    override fun save(coupon: Coupon) {
        val entity = couponMapper.toCouponEntity(coupon)
        couponJpaRepository.save(entity)
    }
}