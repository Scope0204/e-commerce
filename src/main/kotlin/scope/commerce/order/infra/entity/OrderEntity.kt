package scope.commerce.order.infra.entity;

import jakarta.persistence.Entity

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity
import scope.commerce.common.type.order.OrderStatus
import scope.commerce.user.infra.entity.UserCouponEntity
import scope.commerce.user.infra.entity.UserEntity
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
    userEntity: UserEntity,
    orderAt: LocalDateTime,
    status: OrderStatus,
    fromBucket: Boolean,
    totalAmount: Long,
    discountAmount: Long,
    finalAmount: Long
) : BaseEntity()  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var userEntity: UserEntity = userEntity
        internal set

    @Column(name = "order_at", nullable = false)
    var orderAt: LocalDateTime = orderAt
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderStatus = status
        protected set

    @Column(name = "from_bucket", nullable = false)
    var fromBucket: Boolean = fromBucket
        protected set

    @Column(name = "total_amount", nullable = false)
    var totalAmount: Long = totalAmount
        protected set

    @Column(name = "discount_amount", nullable = false)
    var discountAmount: Long = discountAmount
        protected set

    @Column(name = "final_amount", nullable = false)
    var finalAmount: Long = finalAmount
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_coupon_id", unique = true)
    var userCouponEntity: UserCouponEntity? = null
        internal set

    @OneToMany(mappedBy = "orderEntity", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    var orderProducts: MutableList<OrderProductEntity> = mutableListOf()
        internal set

    fun addOrderProduct(orderProduct: OrderProductEntity) {
        orderProducts.add(orderProduct)
        orderProduct.orderEntity = this // 양방향 연결을 위함. setter 활성화
    }
}

