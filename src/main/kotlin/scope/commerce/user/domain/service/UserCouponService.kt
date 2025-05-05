package scope.commerce.user.domain.service

import org.springframework.stereotype.Service
import scope.commerce.user.domain.model.UserCoupon
import scope.commerce.user.domain.repository.UserCouponRepository
import java.time.LocalDate

@Service
class UserCouponService(
    private val userCouponRepository: UserCouponRepository
) {
    /**
     * 이미 해당 쿠폰을 발급받았는지 확인
     */
    fun hasIssued(userId: Long, couponId: Long): Boolean {
        return userCouponRepository.existsByUserIdAndCouponId(userId, couponId)
    }

    /**
     * 유저 쿠폰 등록
     */
    fun issueUserCoupon(userId: Long, couponId: Long) : UserCoupon {
        val userCoupon = UserCoupon(
            userId = userId,
            couponId = couponId,
            issuedAt = LocalDate.now()
        )
        userCouponRepository.save(userCoupon)
        return userCoupon
    }
}
