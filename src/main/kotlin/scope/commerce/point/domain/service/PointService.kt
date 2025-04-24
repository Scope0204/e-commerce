package scope.commerce.point.domain.service

import org.springframework.stereotype.Service
import scope.commerce.point.domain.model.Point
import scope.commerce.point.domain.repository.PointRepository

@Service
class PointService(
    private val pointRepository: PointRepository
) {
    fun getUserPoint(userId: Long): Point {
        return pointRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("해당 유저의 포인트가 존재하지 않습니다. userId=$userId")
    }

    fun charge(userId: Long, amount: Long): Point {
        val point = getUserPoint(userId)
        point.charge(amount)
        return pointRepository.save(point)
    }

    fun use(userId: Long, amount: Long): Point {
        val point = getUserPoint(userId)
        point.use(amount)
        return pointRepository.save(point)
    }
}