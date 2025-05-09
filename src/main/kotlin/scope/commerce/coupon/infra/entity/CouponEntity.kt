package scope.commerce.coupon.infra.entity

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity
import java.time.LocalDate

@Entity
@Table(name = "coupon")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discount_type")
abstract class CouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    name: String,
    remainingQuantity: Long,
    maxDiscountAmount: Long,
    maxQuantity: Long,
    expiryAt: LocalDate
) : BaseEntity() {

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var remainingQuantity: Long = remainingQuantity // 남은 쿠폰 수량
        protected set

    @Column(nullable = false)
    var maxDiscountAmount: Long = maxDiscountAmount // 최대 할인 금액
        protected set

    @Column(nullable = false)
    var maxQuantity: Long = maxQuantity // 최대 발급 수량
        protected set

    @Column(nullable = false)
    var expiryAt: LocalDate = expiryAt // 만료 일자
        protected set
    /**
     * 할인 금액 계산 로직 (서브 클래스에서 구현)
     */
    abstract fun calculateDiscount(orderAmount: Long, quantity: Long = 1): Long
}