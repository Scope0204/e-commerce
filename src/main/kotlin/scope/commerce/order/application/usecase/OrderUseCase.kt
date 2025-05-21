package scope.commerce.order.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.common.util.PurchaseNumberGenerator
import scope.commerce.coupon.domain.service.CouponService
import scope.commerce.order.application.dto.command.OrderCommand
import scope.commerce.order.application.dto.response.OrderResponse
import scope.commerce.order.domain.service.OrderService
import scope.commerce.product.domain.model.StockHistory
import scope.commerce.product.domain.model.StockResult
import scope.commerce.product.domain.service.ProductService
import scope.commerce.product.domain.service.StockHistoryService
import scope.commerce.user.domain.service.UserService

@Service
class OrderUseCase(
    private val purchaseNumberGenerator: PurchaseNumberGenerator,
    private val userService: UserService,
    private val productService: ProductService,
    private val stockHistoryService: StockHistoryService,
    private val couponService: CouponService,
    private val orderService: OrderService
){
    /**
     * 주문을 진행한다.
     * 1. 사용자 ID 회원 인증
     * 2. 상품 목록을 확인 후 재고 사용량 기록
     * 3. 쿠폰 사용 여부 확인 후 상태 변경(쿠폰 재고 차감)
     * 4. 주문 목록 생성 후 저장
     * 5. 결제 시스템 호출
     */
    @Transactional
    fun execute(command: OrderCommand): OrderResponse {
        // 1) 사용자 존재 여부 확인
        val user = userService.findById(command.userId)
        // 주문 번호 발급. TODO : 의미 있는 주문 번호 생성 규칙을 생각하여 적용할 것. 해당 내용이 도메인에 종속되면 Domain 내부에 위치.
        val purchaseNumber = purchaseNumberGenerator.generate()
        // 2) 상품 목록 확인 (단품도 List 로 받아서 처리)
        val products = command.products.map {
            val product = productService.getProductById(it.productId) // 상품 정보 조회
            val stockHistory = StockHistory.create(product,user,purchaseNumber)
            // 재고 사용량 기록을 위한 재고 선점 처리 로직 (Redis + StockHistory 기록)
            val result = stockHistoryService.increaseStock(
                stockHistory = stockHistory,
                totalQuantity = product.quantity,
                perLimitTotalQuantity = Long.MAX_VALUE // 제한 수량 없음.
            )
            if (result != StockResult.SUCCESS) {
                // TODO : 실패 시 비동기 이벤트 발행(재고 사용량 감소를 위한 eventPublisher.publishEvent)
                throw IllegalStateException("재고 선점 실패: productId=${product.id}, purchaseNumber=$purchaseNumber")
            }
            product
        }
        // 3) 쿠폰이 존재하는 경우 유효성 확인
        val coupon = command.couponId?.let { couponService.validateCoupon(it) }

        // 4) 주문 목록 생성 후 저장
        val order = orderService.createOrder(user, purchaseNumber, command.fromBucket, products, coupon)
        val orderResult = orderService.saveOrder(order);

        // TODO : 5) 결제 시스템 호출은 이벤트로 처리 예정 (예: Kafka 발행)
        return OrderResponse(
            orderId = orderResult.id,
            userCouponId = coupon?.id,
            totalAmount = orderResult.totalAmount,
            discountAmount = orderResult.discountAmount,
            finalAmount = orderResult.finalAmount,
            orderedAt = orderResult.orderAt
        )
    }
}


