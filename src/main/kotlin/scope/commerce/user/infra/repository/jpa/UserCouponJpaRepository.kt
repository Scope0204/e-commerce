package scope.commerce.user.infra.repository.jpa

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.user.infra.entity.UserCouponEntity

interface UserCouponJpaRepository : JpaRepository<UserCouponEntity, Long> {
    fun existsByUserEntityIdAndCouponEntityId(userId: Long, couponId: Long): Boolean
    fun findByUserEntityId(userId: Long, pageable: Pageable): Page<UserCouponEntity>
}