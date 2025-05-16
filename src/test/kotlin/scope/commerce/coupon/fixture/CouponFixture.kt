package scope.commerce.coupon.fixture

import org.springframework.stereotype.Component
import scope.commerce.common.type.coupon.CouponDiscountType
import scope.commerce.coupon.domain.model.AmountDiscountCoupon
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.coupon.domain.model.PercentageDiscountCoupon
import scope.commerce.coupon.domain.repository.CouponRepository
import java.time.LocalDate

@Component
class CouponFixture(
    private val couponRepository: CouponRepository
) {
    private val savedCoupons = mutableListOf<Coupon>()

    fun prepareCoupons(count: Int, type: CouponDiscountType) {
        val now = LocalDate.now()
        val coupons = when (type) {
            CouponDiscountType.FIXED -> (1..count).map { i ->
                AmountDiscountCoupon(
                    id = 0L,
                    name = "FIXED-COUPON-$i",
                    remainingQuantity = 100,
                    maxDiscountAmount = 5000,
                    maxQuantity = 100,
                    expiryAt = now.plusDays(30),
                    discountAmount = 1000L * i
                )
            }
            CouponDiscountType.PERCENTAGE -> (1..count).map { i ->
                PercentageDiscountCoupon(
                    id = 0L,
                    name = "PERCENTAGE-COUPON-$i",
                    remainingQuantity = 100,
                    maxDiscountAmount = 5000,
                    maxQuantity = 100,
                    expiryAt = now.plusDays(30),
                    discountRate = 5L * i
                )
            }
        }

        val persisted = couponRepository.saveAll(coupons)
        savedCoupons.clear()
        savedCoupons.addAll(persisted)
    }

    fun getAllSavedCoupons(): List<Coupon> = savedCoupons
}
