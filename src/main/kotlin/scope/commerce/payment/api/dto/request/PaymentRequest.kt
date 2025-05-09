package scope.commerce.payment.api.dto.request

class PaymentRequest {
    data class Execute(
        val userId: Long,
        val orderId: Long
    )
}
