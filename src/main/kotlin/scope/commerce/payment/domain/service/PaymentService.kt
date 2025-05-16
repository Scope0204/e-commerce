package scope.commerce.payment.domain.service

import org.springframework.stereotype.Service
import scope.commerce.common.type.payment.PaymentStatus
import scope.commerce.order.domain.model.Order
import scope.commerce.payment.domain.model.Payment
import scope.commerce.payment.domain.repository.PaymentRepository
import scope.commerce.user.domain.model.User
import java.time.LocalDateTime

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun createPayment(order: Order, user: User): Payment {
        val payment = Payment.create(order, user)
        return paymentRepository.save(payment)
    }
}