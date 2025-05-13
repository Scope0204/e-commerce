package scope.commerce.order.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.order.infra.entity.OrderEntity

interface OrderJpaRepository : JpaRepository<OrderEntity, Long>
