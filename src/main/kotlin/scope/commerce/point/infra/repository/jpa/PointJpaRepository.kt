package scope.commerce.point.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.point.infra.entity.PointEntity

interface PointJpaRepository : JpaRepository<PointEntity, Long> {
    fun findByUserId(userId: Long): PointEntity?
}