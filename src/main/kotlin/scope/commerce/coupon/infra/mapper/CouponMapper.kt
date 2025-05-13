package scope.commerce.coupon.infra.mapper

import org.mapstruct.*
import scope.commerce.coupon.domain.model.AmountDiscountCoupon
import scope.commerce.coupon.domain.model.Coupon
import scope.commerce.coupon.domain.model.PercentageDiscountCoupon
import scope.commerce.coupon.infra.entity.AmountDiscountCouponEntity
import scope.commerce.coupon.infra.entity.CouponEntity
import scope.commerce.coupon.infra.entity.PercentageDiscountCouponEntity

@Mapper(
    componentModel = "spring",
    subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface CouponMapper {

    @SubclassMapping(source = PercentageDiscountCouponEntity::class, target = PercentageDiscountCoupon::class)
    @SubclassMapping(source = AmountDiscountCouponEntity::class, target = AmountDiscountCoupon::class)
    fun toCoupon(entity: CouponEntity): Coupon

    @SubclassMapping(source = PercentageDiscountCoupon::class, target = PercentageDiscountCouponEntity::class)
    @SubclassMapping(source = AmountDiscountCoupon::class, target = AmountDiscountCouponEntity::class)
    fun toCouponEntity(domain: Coupon): CouponEntity
}
