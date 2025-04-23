package scope.commerce.coupon.infra.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var name: String,
    var discountType: String,
    var discountValue: Int,
    var maxDiscountAmount: Int,
    var maxQuantity: Int,
    var expiryAt: LocalDate
)