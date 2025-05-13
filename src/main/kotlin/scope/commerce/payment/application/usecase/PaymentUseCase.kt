package scope.commerce.payment.application.usecase;

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.order.domain.service.OrderService
import scope.commerce.payment.application.dto.command.PaymentCommand
import scope.commerce.payment.domain.service.PaymentService
import scope.commerce.point.domain.service.PointService
import scope.commerce.product.domain.service.ProductStockService
import scope.commerce.user.domain.service.UserService

@Service
class PaymentUseCase(
    private val userService: UserService,
    private val orderService: OrderService,
    private val pointService: PointService,
    private val productStockService: ProductStockService,
    private val paymentService: PaymentService
) {

    /**
     * 결제를 진행한다.
     * 1. 사용자 검증 (UserService)
     * 2. 주문 검증 및 상태 변경 (OrderService)
     * 3. 포인트 사용 (PointService)
     * 4. 재고 차감 (ProductStockService)
     * 5. 결제 정보 생성 (PaymentService)
     */
    @Transactional
    fun executePayment(command: PaymentCommand) {
        val user = userService.findById(command.userId)

        val order = orderService.findByOrderId(command.orderId)
        order.validateOrderStatus()
        order.completeOrder()
        orderService.saveOrder(order)

        pointService.use(user.id, order.finalAmount)

        productStockService.decrease(order.products)

        val payment = paymentService.createPayment(order, user)
        // TODO: 결제 전송은 비동기 이벤트 처리로 트랜잭션 분리 (EventPublisher.publish)
    }

}
