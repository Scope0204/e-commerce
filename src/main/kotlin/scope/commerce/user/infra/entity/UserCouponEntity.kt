package scope.commerce.user.infra.entity

import jakarta.persistence.*
import scope.commerce.coupon.infra.entity.CouponEntity
import java.time.LocalDate

@Entity
@Table(name = "user_coupon")
class UserCouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var userEntity: UserEntity,
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    var couponEntity: CouponEntity,
    var issueDate: LocalDate,
    var used: Boolean
)