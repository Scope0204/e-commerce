package scope.commerce.user.infra.entity

import jakarta.persistence.*
import scope.commerce.coupon.infra.entity.Coupon
import java.time.LocalDate

@Entity
@Table(name = "user_coupon")
class UserCoupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    var coupon: Coupon,
    var issueDate: LocalDate,
    var used: Boolean
)