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
        purchaseNumber: String,
        fromBucket: Boolean,
        products: List<Product>,
        coupon: Coupon?
    ): Order {
        // 주문 상품 생성 (OrderProduct)
        val orderProducts = products.map { OrderProduct.create(it) }

        // 주문 생성
        return Order.create(user,purchaseNumber,fromBucket,coupon,orderProducts)
    }

    fun saveOrder(order: Order): Order {
        return orderRepository.save(order)
    }

    // 주문 조회
    fun findByOrderId(orderId: Long): Order {
        return orderRepository.findById(orderId)
    }
}
