package scope.commerce.point.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.junit.jupiter.Testcontainers
import scope.commerce.point.application.dto.command.ChargePointCommand
import scope.commerce.point.fixture.PointFixture
import scope.commerce.user.fixture.UserFixture
import kotlin.test.Test


@Testcontainers
@SpringBootTest
class ChargePointUseCaseTest {
    @Autowired
    lateinit var chargePointUseCase: ChargePointUseCase

    @Autowired
    lateinit var getPointUseCase: GetPointUseCase

    @Autowired
    lateinit var userFixture: UserFixture

    @Test
    @Transactional
    fun `포인트 충전 후 증가 된 포인트 조회`() {
        // given
        val users = userFixture.prepareUsersWithPoints(5, 0L)
        val userId = users.first().id!!

        // when
        chargePointUseCase.execute(ChargePointCommand(userId, 1000L))

        // then
        val result = getPointUseCase.execute(userId)
        assertThat(result.userId).isEqualTo(userId)
        assertThat(result.currentAmount).isEqualTo(1000L)
    }
}