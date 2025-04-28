package scope.commerce.product.application.usecase

import org.springframework.stereotype.Service
import scope.commerce.product.application.dto.response.ProductQueryResponse
import scope.commerce.product.domain.service.ProductService
import scope.commerce.product.domain.service.ProductStockService

@Service
class QueryProductUseCase (
    private val productService: ProductService,
    private val productStockService: ProductStockService,
) {
    // 상품 정보 조회(단건)
    fun getProductById(productId: Long): ProductQueryResponse {
        val product = productService.getProductById(productId)
        val productStock = productStockService.getStockByProductId(productId)

        return ProductQueryResponse(
            productId = product.id,
            name = product.name,
            price = product.price,
            quantity = productStock.quantity
        )
    }

    // TODO 상품 목록 조회
}