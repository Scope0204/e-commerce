package scope.commerce.payment.api.dto.response

import scope.commerce.payment.application.dto.PaymentServiceDto
import java.time.LocalDateTime

class PaymentResponse {
    data class Detail(
        val paymentId: Long,
        val paymentAmount: Int,
        val paymentStatus: String,
        val paidAt: LocalDateTime
    ) {
        companion object {
            fun from(dto: PaymentServiceDto): Detail =
                Detail(
                    paymentId = dto.paymentId,
                    paymentAmount = dto.paymentAmount,
                    paymentStatus = dto.paymentStatus,
                    paidAt = dto.paidAt
                )
        }
    }
}
