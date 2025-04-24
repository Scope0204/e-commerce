package scope.commerce.payment.infra.entity;

import jakarta.persistence.*
import scope.commerce.order.infra.entity.OrderEntity
import java.time.LocalDateTime

@Entity
class PaymentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity,
    var paymentAmount: Long,
    var paymentStatus: String,
    var paymentAt: LocalDateTime
)
