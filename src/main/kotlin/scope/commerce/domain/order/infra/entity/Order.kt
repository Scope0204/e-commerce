package scope.commerce.domain.order.infra.entity;

import jakarta.persistence.Entity

import jakarta.persistence.*
import scope.commerce.domain.user.infra.entity.User
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    var orderAt: LocalDateTime,
    var status: String,
    var fromBucket: Boolean
)
