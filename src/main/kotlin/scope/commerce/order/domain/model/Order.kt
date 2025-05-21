package scope.commerce.order.domain.model

import scope.commerce.common.type.order.OrderStatus
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.user.domain.model.User
import java.time.LocalDateTime

class Order (
    val id: Long? = null,
    val userId: Long,
    val purchaseNumber: String,
    var status: OrderStatus,
    val fromBucket: Boolean? = false,
    val userCouponId: Long?,
    val totalAmount: Long,
    val discountAmount: Long,
    val finalAmount: Long,
    val orderAt: LocalDateTime,
    val products: List<OrderProduct> = listOf(),
) {
    companion object {
        fun create(user: User, purchaseNumber: String, fromBucket: Boolean, coupon: Coupon?, orderProducts: List<OrderProduct>): Order {
            requireNotNull(user.id) { "유저 ID가 없습니다." }
            // 1. 총 금액 계산
            val totalAmount = orderProducts.sumOf { it.unitPrice * it.quantity }
            // 2. 할인 금액 계산
            val discountAmount = coupon?.calculateDiscount(totalAmount) ?: 0L
            // 3. 최종 결제 금액 계산
            val finalAmount = totalAmount - discountAmount
            
            return Order(
                userId = user.id,
                purchaseNumber = purchaseNumber,
                status = OrderStatus.PENDING,
                fromBucket = fromBucket,
                userCouponId = coupon?.id,
                totalAmount = totalAmount,
                discountAmount = discountAmount,
                finalAmount = finalAmount,
                orderAt = LocalDateTime.now(),
                products = orderProducts
            )
        }
    }

    fun validateOrderStatus() {
        require(status == OrderStatus.PENDING) {
            "주문 대기 상태가 아닙니다. 현재 상태: $status"
        }
    }

    fun completeOrder() {
        require(status == OrderStatus.PENDING) {
            "주문 완료는 PENDING 상태에서만 가능합니다. 현재 상태: $status"
        }
        status = OrderStatus.COMPLETED
    }

    fun cancelOrder() {
        require(status != OrderStatus.COMPLETED) {
            "이미 완료된 주문은 취소할 수 없습니다."
        }
        status = OrderStatus.CANCELED
    }
}