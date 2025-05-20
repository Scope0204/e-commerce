package scope.commerce.product.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.product.domain.model.StockHistory
import scope.commerce.product.domain.repository.StockHistoryRepository
import scope.commerce.product.infra.mapper.StockHistoryMapper
import scope.commerce.product.infra.repository.jpa.StockHistoryJpaRepository

@Repository
class StockHistoryRepositoryImpl(
    private val stockHistoryMapper: StockHistoryMapper,
    private val stockHistoryJpaRepository: StockHistoryJpaRepository
): StockHistoryRepository {
    override fun save(stockHistory: StockHistory) {
        stockHistoryJpaRepository.save(stockHistoryMapper.toStockHistoryEntity(stockHistory))
    }
}