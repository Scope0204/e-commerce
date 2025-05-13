package scope.commerce.order.domain.model

import scope.commerce.common.type.order.OrderStatus
import java.time.LocalDateTime

class Order (
    val id: Long? = null,
    val userId: Long,
    var status: OrderStatus,
    val fromBucket: Boolean? = false,
    val userCouponId: Long?,
    val totalAmount: Long,
    val discountAmount: Long,
    val finalAmount: Long,
    val orderAt: LocalDateTime,
    val products: List<OrderProduct> = listOf(),
) {
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