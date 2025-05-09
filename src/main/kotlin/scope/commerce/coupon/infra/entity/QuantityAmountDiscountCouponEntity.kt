package scope.commerce.coupon.infra.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
@DiscriminatorValue("QUANTITY_AMOUNT")
class QuantityAmountDiscountCouponEntity(
    id: Long?,
    name: String,
    remainingQuantity: Long,
    maxDiscountAmount: Long,
    maxQuantity: Long,
    expiryAt: LocalDate,
    val discountAmountPerUnit: Long, // 개당 할인 금액
) : CouponEntity(id, name, remainingQuantity, maxDiscountAmount, maxQuantity, expiryAt) {
    /**
     * 수량 별 할인
     * - 예 : 1개당 1000원 할인, 최대 5000원까지
     */
    override fun calculateDiscount(orderAmount: Long, quantity: Long): Long {
        val totalDiscount = discountAmountPerUnit * quantity
        return minOf(totalDiscount, maxDiscountAmount)
    }
}
