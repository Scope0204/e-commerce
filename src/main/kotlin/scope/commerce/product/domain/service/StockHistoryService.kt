package scope.commerce.product.domain.service

import org.springframework.stereotype.Service
import scope.commerce.product.domain.model.Stock
import scope.commerce.product.domain.model.StockHistory
import scope.commerce.product.domain.model.StockResult
import scope.commerce.product.domain.repository.StockHistoryRepository
import scope.commerce.product.domain.repository.StockUsageRepository

@Service
class StockHistoryService(
    private val productService: ProductService,
    private val stockUsageRepository: StockUsageRepository,
    private val stockHistoryRepository: StockHistoryRepository
) {
    /**
     * 재고 선점 로직 작성
     * - 총 재고 초과 방지
     * - Redis의 Set 자료구조를 이용해 재고 사용량을 저장 (SADD)
     * - Redis 에서 사용량을 체크한 후 선점 처리
     * - RDB에는 StockHistory 를 기록 (type: PLUS)
     */
    @Synchronized
    fun increaseStock(stockHistory: StockHistory, totalQuantity: Long, perLimitTotalQuantity: Long): StockResult {
        val stock = Stock(
            productId = stockHistory.productId,
            orderId = stockHistory.orderId,
            userId = stockHistory.userId,
            price = stockHistory.price,
            purchaseNumber = stockHistory.purchaseNumber,
        )

        // 2) 유효성 검증 (총 재고 초과 방지를 위한, 전체 재고 수량 체크)
        return if (validateTotalQuantity(totalQuantity, stock)) {
            // 3) 재고 사용량 증가 (Redis SADD)
            stockUsageRepository.addUsage(stock)
            println(">>> 저장 직전 purchaseNumber = ${stock.purchaseNumber}")

            // 4) 히스토리 정보 추가
            stockHistoryRepository.save(stockHistory)
            // 재고 품절처리
            checkSoldOut(totalQuantity, stock)
            StockResult.SUCCESS
        } else {
            StockResult.TOTAL_LIMIT
        }
    }

    private fun validationMemberQuantity() {
        TODO("회원 별 제품 중복 구매 방지를 도입하는 경우, 별도의 로직 추가 필요(ex: stockUsageRepository.getMemberUsedCount(stock)")
    }

    private fun validateTotalQuantity(
        totalQuantity: Long,
        stock: Stock
    ): Boolean {
        return validateQuantity(total = totalQuantity, quantity= stockUsageRepository.getTotalUsedCount(stock))
    }

    private fun validateQuantity(total: Long, quantity: Long): Boolean {
        return total > quantity
    }

    private fun checkSoldOut(totalQuantity: Long, stock: Stock) {
        val totalUsedCount = stockUsageRepository.getTotalUsedCount(stock)
        if (totalQuantity <= totalUsedCount) {
            // 사용량이 총 수량 보다 많은 경우, 상품 상태를 매진 처리.
            productService.updateSoldOut(stock.productId)
        }
    }

}