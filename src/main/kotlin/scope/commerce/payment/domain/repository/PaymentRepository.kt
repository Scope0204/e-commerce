package scope.commerce.payment.domain.repository

import scope.commerce.payment.domain.model.Payment

interface PaymentRepository {
    fun save(payment: Payment) : Payment
}