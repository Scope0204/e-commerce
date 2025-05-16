package scope.commerce.point.infra.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.point.domain.model.Point
import scope.commerce.point.infra.entity.PointEntity

@Mapper(componentModel = "spring")
interface PointMapper {
    fun toPoint(entity: PointEntity): Point
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toPointEntity(domain: Point): PointEntity

    fun toPoints(entity: List<PointEntity>): List<Point>
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toPointEntities(domain: List<Point>): List<PointEntity>
}