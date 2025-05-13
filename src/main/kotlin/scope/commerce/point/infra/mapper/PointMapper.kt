package scope.commerce.point.infra.mapper

import org.mapstruct.Mapper
import scope.commerce.point.domain.model.Point
import scope.commerce.point.infra.entity.PointEntity

@Mapper(componentModel = "spring")
interface PointMapper {
    fun toPoint(entity: PointEntity): Point
    fun toPointEntity(domain: Point): PointEntity
}