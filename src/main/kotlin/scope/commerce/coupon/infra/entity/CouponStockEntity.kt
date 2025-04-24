package scope.commerce.coupon.infra.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "coupon_stock")
class CouponStockEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    @JoinColumn(name = "coupon_id", nullable = false, unique = true)
    val couponEntity: CouponEntity,
    val remainingQuantity: Long,
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
