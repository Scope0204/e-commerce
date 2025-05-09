package scope.commerce.product.api.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.product.api.dto.response.ProductApiResponse
import scope.commerce.product.application.mapper.ProductResponseMapper
import scope.commerce.product.application.usecase.QueryProductUseCase

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val queryProductUseCase: QueryProductUseCase,
    private val productResponseMapper: ProductResponseMapper
) {

    // 단건 상품 조회
    @Operation(summary = "상품 조회(단건) API")
    @GetMapping("/products/{productId}")
    fun getProduct (
        @PathVariable productId : Long,
    ): ApiResponse<ProductApiResponse.Product> {
        return ApiResponse.success(
            productResponseMapper.toApiResponse(queryProductUseCase.getProductById(productId))
        );
    }

    // 상품 목록을 조회
    @Operation(summary = "상품 목록 조회 API")
    @GetMapping
    fun getProducts(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ApiResponse<List<ProductApiResponse.Product>> {
        val response = queryProductUseCase.getProducts(page, size)
        return ApiResponse.success(
            content = productResponseMapper.toApiResponse(response),
            pagination = productResponseMapper.toPagination(response)
        )
    }

    // TODO : 상위 상품 목록 3개를 조회
    /*
    @GetMapping("/products/ranking")
    fun getRankedProducts (
    ): ApiResponse<List<ProductApiResponse.RankedProduct>> {
        val mockRankedProducts = listOf(
            RankedProductServiceDto(1, "oreo", 10000, 10),
            RankedProductServiceDto(2, "chips", 5000, 20),
            RankedProductServiceDto(3, "cola", 2000, 15)
        )
        val responseDto = mockRankedProducts.map { ProductApiResponse.RankedProduct.from(it) }
        return ApiResponse.success(responseDto);
    }
     */
}