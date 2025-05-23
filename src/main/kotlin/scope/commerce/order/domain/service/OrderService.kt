package scope.commerce.order.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.common.type.order.OrderStatus
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.order.domain.model.Order
import scope.commerce.order.domain.model.OrderProduct
import scope.commerce.order.domain.repository.OrderRepository
import scope.commerce.product.domain.model.Product
import scope.commerce.user.domain.model.User

@Service
class OrderService(
    private val orderRepository: OrderRepository
){
    fun createOrder(
        user: User,
        fromBucket: Boolean,
        products: List<Product>,
        coupon: Coupon?
    ): Order {
        // 1. 주문 상품 생성 (OrderProduct)
        val orderProducts = products.map {
            // 수량은 product 에 포함되어 있다고 가정하거나, 별도 파라미터로 받아야 함
            OrderProduct(
                productId = it.id!!, // TODO : 팩토리 메서드로 변경 후 검증 로직 추가 필요
                quantity = it.quantity,
                unitPrice = it.price,
            )
        }

        // 2. 총 금액 계산
        val totalAmount = orderProducts.sumOf { it.unitPrice * it.quantity }

        // 3. 할인 금액 계산
        val discountAmount = coupon?.calculateDiscount(totalAmount) ?: 0L

        // 4. 최종 결제 금액 계산
        val finalAmount = totalAmount - discountAmount

        // 5. 주문 도메인 객체 생성
        return Order(
            userId = user.id!!, // TODO : Payment 모델과 마찬가지로 도메인 객체에 팩토리 메서드로 생성 책임 위임하도록 변경
            status = OrderStatus.PENDING,
            fromBucket = fromBucket,
            userCouponId = coupon?.id,
            totalAmount = totalAmount,
            discountAmount = discountAmount,
            finalAmount = finalAmount,
            orderAt = java.time.LocalDateTime.now(),
            products = orderProducts
        )
    }

    fun saveOrder(order: Order): Order {
        return orderRepository.save(order)
    }

    // 주문 조회
    fun findByOrderId(orderId: Long): Order {
        return orderRepository.findById(orderId)
    }
}
