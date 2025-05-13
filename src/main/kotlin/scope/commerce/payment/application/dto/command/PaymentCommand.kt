package scope.commerce.payment.application.dto.command

data class PaymentCommand (
    val userId: Long,
    val orderId: Long
)