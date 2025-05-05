package scope.commerce.user.infra.entity

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity
import scope.commerce.coupon.infra.entity.CouponEntity
import java.time.LocalDate

@Entity
@Table(name = "user_coupon")
class UserCouponEntity(
    userEntity: UserEntity,
    couponEntity: CouponEntity,
    issuedAt: LocalDate,
    used: Boolean
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var userEntity: UserEntity = userEntity
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id" , nullable = false)
    var couponEntity: CouponEntity = couponEntity
        protected set

    @Column(nullable = false)
    var issuedAt: LocalDate = issuedAt
        protected set

    @Column(nullable = false)
    var used: Boolean = used
        protected set
}