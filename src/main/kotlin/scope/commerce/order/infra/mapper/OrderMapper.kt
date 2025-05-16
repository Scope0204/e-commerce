package scope.commerce.order.infra.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.order.domain.model.Order
import scope.commerce.order.infra.entity.OrderEntity
import scope.commerce.user.infra.entity.UserEntity

@Mapper(componentModel = "spring")
interface OrderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userEntity.id", target = "userId")
    @Mapping(source = "orderProducts", target = "products")
    fun toOrder(entity: OrderEntity): Order

    @InheritInverseConfiguration
    @Mapping(target = "userEntity", source = "userEntity")
    @Mapping(target = "userCouponEntity", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toOrderEntity(
        domain: Order,
        userEntity: UserEntity,
    ): OrderEntity
}