package scope.commerce.application.payment.dto;

import java.time.LocalDateTime

data class PaymentServiceDto(
    val paymentId: Long,
    val paymentAmount: Int,
    val paymentStatus: String,
    val paidAt: LocalDateTime
)

