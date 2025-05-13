package scope.commerce.payment.application.dto.response;

import java.time.LocalDateTime

data class PaymentResponse(
    val paymentId: Long,
    val paymentAmount: Int,
    val paymentStatus: String,
    val paidAt: LocalDateTime
)

