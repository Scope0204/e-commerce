package scope.commerce.product.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.product.application.dto.response.ProductListQueryResponse
import scope.commerce.product.application.dto.response.ProductQueryResponse
import scope.commerce.product.domain.service.ProductService

@Service
class QueryProductUseCase (
    private val productService: ProductService,
) {
    // 상품 정보 조회(단건)
    @Transactional(readOnly = true)
    fun getProductById(productId: Long): ProductQueryResponse {
        val product = productService.getProductById(productId)
        return ProductQueryResponse(
            productId = product.id!!, // TODO : 팩토리 메서드로 변경 후 검증 로직 추가 필요
            name = product.name,
            price = product.price,
            quantity = product.quantity
        )
    }

    // 상품 목록 조회
    @Transactional(readOnly = true)
    fun getProducts(page: Int, size: Int): ProductListQueryResponse {
        val productPage = productService.getProducts(page, size)
        val products = productPage.content.map { product ->
            ProductQueryResponse(
                productId = product.id!!,  // TODO : 팩토리 메서드로 변경 후 검증 로직 추가 필요
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