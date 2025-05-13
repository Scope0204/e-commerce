package scope.commerce.point.fixture

import org.springframework.stereotype.Component
import scope.commerce.point.domain.model.Point
import scope.commerce.point.domain.repository.PointRepository
import scope.commerce.user.domain.model.User
import scope.commerce.user.domain.repository.UserRepository

@Component
class PointFixture(
    private val userRepository: UserRepository,
    private val pointRepository: PointRepository
) {

    fun createPoint(userId: Long, amount: Long = 1000L, id: Long? = null): Point {
        return Point(id = id, userId = userId, amount = amount)
    }

    fun createPointList(userIds: List<Long>, amount: Long = 1000L): List<Point> {
        return userIds.map { userId -> createPoint(userId, amount) }
    }
}
