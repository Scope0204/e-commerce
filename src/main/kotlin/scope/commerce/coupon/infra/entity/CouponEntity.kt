package scope.commerce.coupon.infra.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "coupon")
class CouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var discountType: String,
    var discountValue: Long,
    var maxDiscountAmount: Long,
    var maxQuantity: Long,
    var expiryAt: LocalDate
)