package scope.commerce.coupon.domain.repository

import scope.commerce.coupon.domain.model.Coupon

interface CouponRepository {
    fun findById(couponId: Long): Coupon
    fun save(coupon: Coupon)
}