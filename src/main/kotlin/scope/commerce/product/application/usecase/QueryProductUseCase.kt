package scope.commerce.product.application.usecase

import org.springframework.stereotype.Service
import scope.commerce.product.application.dto.response.ProductListQueryResponse
import scope.commerce.product.application.dto.response.ProductQueryResponse
import scope.commerce.product.domain.service.ProductService

@Service
class QueryProductUseCase (
    private val productService: ProductService,
) {
    // 상품 정보 조회(단건)
    fun getProductById(productId: Long): ProductQueryResponse {
        val product = productService.getProductById(productId)
        return ProductQueryResponse(
            productId = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity
        )
    }

    // 상품 목록 조회
    fun getProducts(page: Int, size: Int): ProductListQueryResponse {
        val productPage = productService.getProducts(page, size)
        val products = productPage.content.map { product ->
            ProductQueryResponse(
                productId = product.id,
                name = product.name,
                price = product.price,
                quantity = product.quantity
            )
        }
        return ProductListQueryResponse(
            products = products,
            page = page,
            size = size,
            totalElements = productPage.totalElements,
            totalPages = productPage.totalPages
        )
    }
}