package scope.commerce.user.infra.repository.impl

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import scope.commerce.coupon.infra.repository.jpa.CouponJpaRepository
import scope.commerce.user.domain.model.UserCoupon
import scope.commerce.user.domain.repository.UserCouponRepository
import scope.commerce.user.infra.mapper.UserCouponMapper
import scope.commerce.user.infra.repository.jpa.UserCouponJpaRepository
import scope.commerce.user.infra.repository.jpa.UserJpaRepository

@Repository
class UserCouponRepositoryImpl(
    private val userCouponMapper: UserCouponMapper,
    private val userJpaRepository: UserJpaRepository,
    private val couponJpaRepository: CouponJpaRepository,
    private val userCouponJpaRepository: UserCouponJpaRepository,
) : UserCouponRepository {

    override fun existsByUserIdAndCouponId(userId: Long, couponId: Long): Boolean {
        return userCouponJpaRepository.existsByUserEntityIdAndCouponEntityId(userId, couponId)
    }

    override fun save(userCoupon: UserCoupon) {
        val userEntity = userJpaRepository.getReferenceById(userCoupon.userId)
        val couponEntity = couponJpaRepository.getReferenceById(userCoupon.couponId)
        val entity = userCouponMapper.toUserCouponEntity(userCoupon, userEntity, couponEntity)
        userCouponJpaRepository.save(entity)
    }

    override fun findByUserEntityId(userId: Long, pageable: Pageable): Page<UserCoupon> {
        return userCouponJpaRepository.findByUserEntityId(userId, pageable)
            .map { userCouponMapper.toUserCoupon(it) }
    }
}