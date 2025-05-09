package scope.commerce.coupon.domain.repository

import scope.commerce.coupon.domain.model.Coupon

interface CouponRepository {
    fun findById(couponId: Long): Coupon
    fun findByIdIn(ids: List<Long>): List<Coupon>
    fun save(coupon: Coupon)
}