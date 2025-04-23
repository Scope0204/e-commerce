package scope.commerce.order.infra.entity;

import jakarta.persistence.*
import scope.commerce.product.infra.entity.Product
import scope.commerce.user.infra.entity.UserCoupon
import java.time.LocalDateTime

@Entity
@Table(name = "order_coupon")
class OrderCoupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,
    @ManyToOne
    @JoinColumn(name = "user_coupon_id")
    var userCoupon: UserCoupon,
    var discountAmount: Int,
    var appliedAt: LocalDateTime
)
