package scope.commerce.coupon.domain.model

import scope.commerce.common.type.coupon.CouponDiscountType
import java.time.LocalDate

abstract class Coupon(
    open val id: Long?,
    open val name: String,
    open val remainingQuantity: Long,
    open val maxDiscountAmount: Long,
    open val maxQuantity: Long,
    open val expiryAt: LocalDate,
) {
    abstract fun calculateDiscount(orderAmount: Long, quantity: Long = 1): Long
    // 공통 반환 값을 위한 추상 프로퍼티
    abstract val discountType: CouponDiscountType
    abstract val discountValue: Long

    /**
     * 쿠폰 유효성 검증 (만료, 재고 확인)
     */
    open fun validateForIssue(quantity: Long = 1) { // 요청 시 1매 발급
        if (isExpired()) {
            throw IllegalStateException("만료된 쿠폰입니다.")
        }
        if (remainingQuantity < quantity) {
            throw IllegalStateException("쿠폰 재고가 부족합니다. 현재 재고: $remainingQuantity, 요청 수량: $quantity")
        }
    }
    /**
     * 쿠폰 만료 여부 확인
     */
    open fun isExpired(): Boolean {
        return expiryAt.isBefore(LocalDate.now()) // 현재 날짜와 비교
    }

    /**
     * 쿠폰 재고 차감
     */
    open fun decrease(quantity: Long = 1) : Coupon {
        require(quantity >= 1) { "차감 수량은 1 이상이어야 합니다." }
        validateForIssue(quantity) // 안전하게 차감되었는 지 검증
        return when (this) {  // 재고 업데이트 (불변이므로 copy 로 반환)
            is AmountDiscountCoupon -> this.copy(remainingQuantity = remainingQuantity - quantity)
            is PercentageDiscountCoupon -> this.copy(remainingQuantity = remainingQuantity - quantity)
            else -> throw IllegalStateException("지원하지 않는 쿠폰 타입입니다.")
        }
    }
}

data class AmountDiscountCoupon(
    override val id: Long,
    override val name: String,
    override val remainingQuantity: Long,
    override val maxDiscountAmount: Long,
    override val maxQuantity: Long,
    override val expiryAt: LocalDate,
    val discountAmount: Long,
) : Coupon(id, name, remainingQuantity, maxDiscountAmount, maxQuantity, expiryAt) {
    override fun calculateDiscount(orderAmount: Long, quantity: Long): Long {
        return minOf(discountAmount, maxDiscountAmount)
    }
    override val discountType: CouponDiscountType = CouponDiscountType.FIXED
    override val discountValue: Long = discountAmount
}

data class PercentageDiscountCoupon(
    override val id: Long,
    override val name: String,
    override val remainingQuantity: Long,
    override val maxDiscountAmount: Long,
    override val maxQuantity: Long,
    override val expiryAt: LocalDate,
    val discountRate: Long,
) : Coupon(id, name, remainingQuantity, maxDiscountAmount, maxQuantity, expiryAt) {
    override fun calculateDiscount(orderAmount: Long, quantity: Long): Long {
        return minOf((orderAmount * discountRate) / 100, maxDiscountAmount)
    }
    override val discountType: CouponDiscountType = CouponDiscountType.PERCENTAGE
    override val discountValue: Long = discountRate
}