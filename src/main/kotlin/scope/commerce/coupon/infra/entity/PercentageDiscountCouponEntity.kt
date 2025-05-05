package scope.commerce.coupon.infra.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
@DiscriminatorValue("PERCENTAGE")
class PercentageDiscountCouponEntity(
    name: String,
    remainingQuantity: Long,
    maxDiscountAmount: Long,
    maxQuantity: Long,
    expiryAt: LocalDate,
    val discountRate: Long,
) : CouponEntity(
    name = name,
    remainingQuantity = remainingQuantity,
    maxDiscountAmount = maxDiscountAmount,
    maxQuantity = maxQuantity,
    expiryAt = expiryAt
) {
    /**
     * 정률 할인 : 총 금액의 비율만큼 할인
     * - 예: 10% 할인
     */
    override fun calculateDiscount(orderAmount: Long, quantity: Long): Long {
        return minOf((orderAmount * discountRate) / 100, maxDiscountAmount)
    }
}
