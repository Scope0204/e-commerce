package scope.commerce.product.infra.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.infra.entity.ProductStockEntity

@Mapper(componentModel = "spring")
interface ProductStockMapper {
    @Mapping(source = "productEntity.id", target = "productId")
    fun toProductStock(entity: ProductStockEntity): ProductStock

    @InheritInverseConfiguration
    @Mapping(target = "productEntity", ignore = true) // 주입 필요
    fun toProductStockEntity(domain: ProductStock): ProductStockEntity
}
