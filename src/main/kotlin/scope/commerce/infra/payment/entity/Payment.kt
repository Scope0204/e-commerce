package scope.commerce.infra.payment.entity;

import jakarta.persistence.*
import scope.commerce.infra.order.entity.Order
import java.time.LocalDateTime

@Entity
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,
    var paymentAmount: Int,
    var paymentStatus: String,
    var paymentAt: LocalDateTime
)
