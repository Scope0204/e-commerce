 package scope.commerce.product.infra.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.infra.entity.ProductEntity
import scope.commerce.product.infra.entity.ProductStockEntity

@Mapper(componentModel = "spring")
interface ProductStockMapper {
    @Mapping(source = "productEntity.id", target = "productId")
    fun toProductStock(entity: ProductStockEntity): ProductStock

    @Mapping(target = "productEntity", source = "productEntity")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toProductStockEntity(domain: ProductStock, productEntity: ProductEntity): ProductStockEntity
}
