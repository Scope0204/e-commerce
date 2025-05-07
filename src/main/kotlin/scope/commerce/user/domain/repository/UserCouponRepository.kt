package scope.commerce.user.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import scope.commerce.user.domain.model.UserCoupon

interface UserCouponRepository {
    fun existsByUserIdAndCouponId(userId: Long, couponId: Long): Boolean
    fun save(userCoupon: UserCoupon)
    fun findByUserEntityId(userId: Long, pageable: Pageable): Page<UserCoupon>
}
