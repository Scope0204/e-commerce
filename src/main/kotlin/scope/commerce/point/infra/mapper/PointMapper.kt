package scope.commerce.point.infra.mapper

import scope.commerce.point.domain.model.Point
import scope.commerce.point.infra.entity.PointEntity

object PointMapper {
    fun toDomain(entity: PointEntity): Point =
        Point(id = entity.id, userId = entity.userId, amount = entity.amount)

    fun toEntity(domain: Point): PointEntity =
        PointEntity(id = domain.id, userId = domain.userId, amount = domain.amount)
}