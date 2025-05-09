package scope.commerce.order.infra.entity;

import jakarta.persistence.*
import scope.commerce.product.infra.entity.ProductEntity
import scope.commerce.user.infra.entity.UserCouponEntity
import java.time.LocalDateTime

@Entity
@Table(name = "order_coupon")
class OrderCouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var productEntity: ProductEntity,
    @ManyToOne
    @JoinColumn(name = "user_coupon_id")
    var userCouponEntity: UserCouponEntity,
    var discountAmount: Long,
    var appliedAt: LocalDateTime
)
