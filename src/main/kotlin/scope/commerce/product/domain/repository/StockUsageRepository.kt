package scope.commerce.product.domain.repository

import scope.commerce.product.domain.model.Stock

interface StockUsageRepository {
    fun getTotalUsedCount(stock: Stock): Long
    fun addUsage(stock: Stock)
}