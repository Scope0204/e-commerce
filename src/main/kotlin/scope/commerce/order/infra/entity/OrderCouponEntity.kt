package scope.commerce.order.infra.entity;

import jakarta.persistence.*
import scope.commerce.product.infra.entity.ProductEntity
import scope.commerce.user.infra.entity.UserCouponEntity
import java.time.LocalDateTime

/**
 * 한번의 주문에는 하나의 쿠폰만 적용시킬 예정
 * - 검토 후 삭제 예정
 * - docs 업데이트 필요
 */
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
