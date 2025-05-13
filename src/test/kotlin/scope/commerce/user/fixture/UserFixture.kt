package scope.commerce.user.fixture

import org.springframework.stereotype.Component
import scope.commerce.point.domain.model.Point
import scope.commerce.point.domain.repository.PointRepository
import scope.commerce.point.fixture.PointFixture
import scope.commerce.user.domain.model.User
import scope.commerce.user.domain.repository.UserRepository

@Component
class UserFixture(
    private val userRepository: UserRepository,
    private val pointRepository: PointRepository,
    private val pointFixture: PointFixture
) {

    fun createUser(name: String = "test-user", id: Long? = null): User {
        return User(id = id, name = name)
    }

    fun createUserList(size: Int): List<User> =
        (0 until size).map { i -> createUser(name = "user-$i") }

    /**
     *  유저 생성 + 저장 + 포인트 생성 + 저장
     */
    fun prepareUsersWithPoints(size: Int, amount: Long): List<User> {
        val users = createUserList(size)
        val savedUsers = userRepository.saveAll(users)
        val points = pointFixture.createPointList(savedUsers.map { it.id!! }, amount)
        pointRepository.saveAll(points)

        return savedUsers
    }
}
