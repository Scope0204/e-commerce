package scope.commerce.payment.domain.model

import scope.commerce.common.type.payment.PaymentStatus
import scope.commerce.order.domain.model.Order
import scope.commerce.user.domain.model.User
import java.time.LocalDateTime

data class Payment (
    val id: Long? = null,
    val userId: Long,
    val orderId: Long,
    val paymentAmount: Long,
    val paymentStatus: PaymentStatus,
    val paidAt: LocalDateTime,
) {
    companion object {
        fun create(order: Order, user: User): Payment {
            requireNotNull(order.id) { "주문 ID가 없습니다." }
            requireNotNull(user.id) { "유저 ID가 없습니다." }

            return Payment(
                userId = user.id,
                orderId = order.id,
                paymentAmount = order.finalAmount,
                paymentStatus = PaymentStatus.PENDING,
                paidAt = LocalDateTime.now()
            )
        }
    }
}