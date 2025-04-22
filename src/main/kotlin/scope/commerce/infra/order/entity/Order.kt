package scope.commerce.infra.order.entity;

import jakarta.persistence.Entity

import jakarta.persistence.*
import scope.commerce.infra.user.entity.User
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
