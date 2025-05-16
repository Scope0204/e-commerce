package scope.commerce.point.domain.repository

import scope.commerce.point.domain.model.Point

interface PointRepository {
    fun findByUserId(userId: Long): Point?
    fun save(point: Point): Point
    fun saveAll(point: List<Point>): List<Point>
    fun deleteAll()
}