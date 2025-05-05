package scope.commerce.user.domain.repository

import scope.commerce.user.domain.model.UserCoupon

interface UserCouponRepository {
    fun existsByUserIdAndCouponId(userId: Long, couponId: Long): Boolean
    fun save(userCoupon: UserCoupon)
}
