package scope.commerce.common.type.coupon

enum class CouponDiscountType {
    FIXED,
    PERCENTAGE;

    companion object {
        fun from(discriminator: String): CouponDiscountType =
            entries.firstOrNull { it.name.equals(discriminator, ignoreCase = true) }
                ?: throw IllegalArgumentException("지원하지 않는 쿠폰 타입입니다: $discriminator")
    }
}