package scope.commerce.product.domain.repository

import scope.commerce.product.domain.model.StockHistory

interface StockHistoryRepository {
    fun save(stockHistory: StockHistory)
}