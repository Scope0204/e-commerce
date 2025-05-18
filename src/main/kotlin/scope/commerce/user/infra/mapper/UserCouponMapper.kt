package scope.commerce.user.infra.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.user.domain.model.UserCoupon
import scope.commerce.user.infra.entity.UserCouponEntity
import scope.commerce.user.infra.entity.UserEntity
import scope.commerce.coupon.infra.entity.CouponEntity

@Mapper(componentModel = "spring")
interface UserCouponMapper {
    @Mapping(source = "userEntity.id", target = "userId")
    @Mapping(source = "couponEntity.id", target = "couponId")
    fun toUserCoupon(entity: UserCouponEntity): UserCoupon

    @Mapping(target = "userEntity", source = "userEntity")
    @Mapping(target = "couponEntity", source = "couponEntity")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toUserCouponEntity(
        domain: UserCoupon,
        userEntity: UserEntity,
        couponEntity: CouponEntity
    ): UserCouponEntity
}