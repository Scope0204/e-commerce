package scope.commerce.payment.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.payment.domain.model.Payment
import scope.commerce.payment.domain.repository.PaymentRepository

@Repository
class PaymentRepositoryImpl(
): PaymentRepository{
    override fun save(payment: Payment): Payment {
        TODO("Not yet implemented")
    }
}