package scope.commerce.payment.domain.model

import scope.commerce.common.type.payment.PaymentStatus
import java.time.LocalDateTime

data class Payment (
    val id: Long? = null,
    val userId: Long,
    val orderId: Long,
    val paymentAmount: Long,
    val paymentStatus: PaymentStatus,
    val paidAt: LocalDateTime,
)