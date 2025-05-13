package scope.commerce.payment.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.payment.infra.entity.PaymentEntity

interface PaymentJpaRepository: JpaRepository<PaymentEntity, Long>