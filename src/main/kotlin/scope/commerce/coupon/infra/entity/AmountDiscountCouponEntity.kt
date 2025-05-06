package scope.commerce.coupon.infra.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@DiscriminatorValue("FIXED")
class AmountDiscountCouponEntity(
    id: Long?,
    name: String,
    remainingQuantity: Long,
    maxDiscountAmount: Long,
    maxQuantity: Long,
    expiryAt: LocalDate,
    val discountAmount: Long, // 정액 할인 금액
) : CouponEntity(id, name, remainingQuantity, maxDiscountAmount, maxQuantity, expiryAt) {
    /**
     * 정액 할인 : 고정 금액 할인
     * - 예: 5000원 할인
     */
    override fun calculateDiscount(orderAmount: Long, quantity: Long): Long {
        return minOf(discountAmount, maxDiscountAmount)
    }
}
