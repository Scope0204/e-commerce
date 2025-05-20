package scope.commerce.product.infra.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.product.domain.model.StockHistory
import scope.commerce.product.infra.entity.StockHistoryEntity

@Mapper(componentModel = "spring")
interface StockHistoryMapper {
    fun toStockHistory(entity: StockHistoryEntity): StockHistory
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toStockHistoryEntity(domain: StockHistory): StockHistoryEntity
}