package scope.commerce.product.application.usecase

import org.springframework.stereotype.Service
import scope.commerce.product.application.dto.response.ProductListQueryResponse
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

    // 상품 목록 조회
    fun getProducts(page: Int, size: Int): ProductListQueryResponse {
        // 1. 상품 리스트 조회
        val productPage = productService.getProducts(page, size)
        val products = productPage.content

        // 2. 상품 id 리스트로 재고 리스트 한 번에 가져옴
        val productIds = products.map { it.id }
        val stocks = productStockService.getStocksByProductIds(productIds)

        // stock 매핑용 Map
        val stockMap = stocks.associateBy { it.productId }

        // 3. product + stock 조합
        val productResponses = products.map { product ->
            val stock = stockMap[product.id]
                ?: throw IllegalStateException("상품 재고를 찾을 수 없습니다. productId=${product.id}")

            ProductQueryResponse(
                productId = product.id,
                name = product.name,
                price = product.price,
                quantity = stock.quantity
            )
        }

        return ProductListQueryResponse(
            products = productResponses,
            page = page,
            size = size,
            totalElements = productPage.totalElements,
            totalPages = productPage.totalPages
        )
    }
}