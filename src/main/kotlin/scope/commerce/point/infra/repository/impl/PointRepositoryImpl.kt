package scope.commerce.point.infra.repository.impl

import org.mapstruct.Mapper
import org.springframework.stereotype.Repository
import scope.commerce.point.domain.model.Point
import scope.commerce.point.domain.repository.PointRepository
import scope.commerce.point.infra.mapper.PointMapper
import scope.commerce.point.infra.repository.jpa.PointJpaRepository

@Repository
class PointRepositoryImpl(
    private val pointMapper: PointMapper,
    private val pointJpaRepository: PointJpaRepository
) : PointRepository {

    override fun findByUserId(userId: Long): Point? {
        return pointJpaRepository.findByUserId(userId)?.let { pointMapper.toPoint(it) }
    }

    override fun save(point: Point): Point {
        val saved = pointJpaRepository.save(pointMapper.toPointEntity(point))
        return pointMapper.toPoint(saved)
    }

    override fun deleteAll() {
        pointJpaRepository.deleteAll()
    }
}