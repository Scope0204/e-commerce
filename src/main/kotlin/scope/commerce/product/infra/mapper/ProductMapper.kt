package scope.commerce.product.infra.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.product.domain.model.Product
import scope.commerce.product.infra.entity.ProductEntity

@Mapper(componentModel = "spring")
interface ProductMapper {

    @Mapping(source = "stock.quantity", target = "quantity")
    fun toProduct(entity: ProductEntity): Product
    fun toProductEntity(domain: Product): ProductEntity
}