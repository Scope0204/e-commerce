package scope.commerce.infra.coupon.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "coupon_stock")
class CouponStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    @JoinColumn(name = "coupon_id", nullable = false, unique = true)
    val coupon: Coupon,
    val remainingQuantity: Int,
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
