package scope.commerce.coupon.infra.repository.impl

import org.hibernate.Hibernate
import org.springframework.stereotype.Repository
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.coupon.domain.repository.CouponRepository
import scope.commerce.coupon.infra.entity.CouponEntity
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

    override fun findByIdIn(ids: List<Long>): List<Coupon> {
        return couponJpaRepository.findByIdIn(ids)
            //.map { couponMapper.toCoupon(it) }
            .map { entity ->
                val realEntity = Hibernate.unproxy(entity) as CouponEntity
                couponMapper.toCoupon(realEntity)
            }
    }

    override fun save(coupon: Coupon) {
        val entity = couponMapper.toCouponEntity(coupon)
        couponJpaRepository.save(entity)
    }
}