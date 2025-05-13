package scope.commerce.payment.infra.entity;

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity
import scope.commerce.common.type.payment.PaymentStatus
import scope.commerce.order.infra.entity.OrderEntity
import scope.commerce.user.infra.entity.UserEntity
import java.time.LocalDateTime

@Entity
@Table(name = "payment")
class PaymentEntity(
    userEntity: UserEntity,
    orderEntity: OrderEntity,
    paymentAmount: Long,
    paymentStatus: PaymentStatus,
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var userEntity: UserEntity = userEntity
        protected set

    @ManyToOne
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity = orderEntity
        protected set

    var paymentAmount: Long = paymentAmount
        protected set

    @Enumerated(EnumType.STRING)
    var paymentStatus: PaymentStatus = paymentStatus
        protected set

    // BaseEntity 에서 생성 업데이트를 관리하므로 고려 후 삭제
    var paymentAt: LocalDateTime? = LocalDateTime.now()
        protected set
}
