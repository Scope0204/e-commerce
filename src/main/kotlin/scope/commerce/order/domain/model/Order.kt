package scope.commerce.order.domain.model

import scope.commerce.common.type.order.OrderStatus
import java.time.LocalDateTime

class Order(
    val id: Long? = null,
    val userId: Long,
    val status: OrderStatus,
    val fromBucket: Boolean? = false,
    val userCouponId: Long?,
    val totalAmount: Long,
    val discountAmount: Long,
    val finalAmount: Long,
    val orderAt: LocalDateTime,
    val products: List<OrderProduct> = listOf(),
)