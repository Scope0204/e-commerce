package scope.commerce.coupon.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import scope.commerce.common.config.IntegrationTestBase
import scope.commerce.common.type.coupon.CouponDiscountType
import scope.commerce.coupon.application.dto.command.IssueCouponCommand
import scope.commerce.coupon.fixture.CouponFixture
import scope.commerce.user.fixture.UserFixture

class CouponUseCaseTest: IntegrationTestBase() {
    @Autowired
    lateinit var issueCouponUseCase: IssueCouponUseCase

    @Autowired
    lateinit var userFixture: UserFixture

    @Autowired
    lateinit var couponFixture: CouponFixture

    private val userCount = 1
    private val couponCount = 1

    @BeforeEach
    fun setUp() {
        userFixture.prepareUsers(userCount)
        couponFixture.prepareCoupons(couponCount,CouponDiscountType.FIXED)
        couponFixture.prepareCoupons(couponCount,CouponDiscountType.PERCENTAGE)
    }

    @Test
    @Transactional
    fun `선착순 쿠폰 발급 성공`() {
        // given
        val users = userFixture.prepareUsersWithPoints(5, 0L)
        val user = users.first()
        val coupon = couponFixture.getAllSavedCoupons().first()

        // when
        val result = issueCouponUseCase.issueCoupon(
            IssueCouponCommand(userId = user.id!!, couponId = coupon.id!!)
        )

        // then
        assertThat(result.couponId).isEqualTo(coupon.id)
        assertThat(result.name).isEqualTo(coupon.name)
        assertThat(result.remainingQuantity).isEqualTo(coupon.remainingQuantity - 1)
        assertThat(result.issuedAt).isNotNull()
    }

    @Test
    @Transactional
    fun `동일 쿠폰 중복 발급 시 예외`() {
        // given
        val user = userFixture.getAllSavedUsers().first()
        val coupon = couponFixture.getAllSavedCoupons().first()
        val command = IssueCouponCommand(user.id!!, coupon.id!!)

        // when
        issueCouponUseCase.issueCoupon(command) // 첫 발급 성공

        // then
        val exception = assertThrows<IllegalStateException> {
            issueCouponUseCase.issueCoupon(command) // 중복 발급 시도
        }
        assertThat(exception.message).isEqualTo("이미 발급받은 쿠폰입니다.")
    }
}
