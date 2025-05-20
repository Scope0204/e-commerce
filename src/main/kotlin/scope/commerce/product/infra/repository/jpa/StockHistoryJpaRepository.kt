package scope.commerce.product.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.product.infra.entity.StockHistoryEntity

interface StockHistoryJpaRepository : JpaRepository<StockHistoryEntity, Long>
