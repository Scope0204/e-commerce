package scope.commerce.user.fixture

import org.springframework.stereotype.Component
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
    private val savedUsers = mutableListOf<User>()

    fun createUser(name: String = "test-user", id: Long? = null): User {
        return User(id = id, name = name)
    }

    fun createUserList(size: Int): List<User> =
        (0 until size).map { i -> createUser(name = "user-$i") }

    fun prepareUsers(size: Int) {
        val users = createUserList(size)
        val persisted = userRepository.saveAll(users)
        savedUsers.clear()
        savedUsers.addAll(persisted)
    }

    /**
     *  유저 생성 + 저장 + 포인트 생성 + 저장
     */
    fun prepareUsersWithPoints(size: Int, amount: Long): List<User> {
        val users = createUserList(size)
        val persisted = userRepository.saveAll(users)
        savedUsers.clear()
        savedUsers.addAll(persisted)

        val points = pointFixture.createPointList(persisted.map { it.id!! }, amount)
        pointRepository.saveAll(points)

        return persisted
    }

    fun getAllSavedUsers(): List<User> = savedUsers
}
