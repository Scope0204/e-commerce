package scope.commerce.payment.application.dto;

import java.time.LocalDateTime

data class PaymentServiceDto(
    val paymentId: Long,
    val paymentAmount: Int,
    val paymentStatus: String,
    val paidAt: LocalDateTime
)

