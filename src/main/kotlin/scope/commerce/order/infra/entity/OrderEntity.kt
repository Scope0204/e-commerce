package scope.commerce.order.infra.entity;

import jakarta.persistence.Entity

import jakarta.persistence.*
import scope.commerce.user.infra.entity.UserEntity
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var userEntity: UserEntity,
    var orderAt: LocalDateTime,
    var status: String,
    var fromBucket: Boolean
)
