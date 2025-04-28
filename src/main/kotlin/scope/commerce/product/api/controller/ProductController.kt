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
/*
    // 상품 목록을 조회
    @GetMapping("/products")
    fun getProducts(
        @RequestParam(defaultValue = "1") @Min(1) page: Int,
        @RequestParam(defaultValue = "10") @Min(1) size: Int
    ): ApiResponse<List<ProductApiResponse.Product>> {
        val result = queryProductUseCase.getProducts(page, size)
        return ApiResponse.success(
            content = productResponseMapper.toApiResponse(result),
            pagination = productResponseMapper.toPagination(result)
        )
    }

    @GetMapping("/products")
    fun getProducts (
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ApiResponse<List<ProductApiResponse.Product>> {
        // TODO : productService.searchList(productId, page, size);
        /*val mockProducts = listOf(
            ProductServiceDto(1, "oreo", 10000, 10),
            ProductServiceDto(2, "chips", 5000, 20),
            ProductServiceDto(3, "cola", 2000, 15)
        )
        val responseDto = mockProducts.map { ProductApiResponse.Product.from(it)};
        // 페이징 정보 생성
        val pagination = ApiResponse.Pagination(
            page = page,
            size = size,
            totalElements = 20, // 예시. 실제로는 서비스에서 토탈 갯수를 제공.
            totalPages = ceil((20 / size).toDouble())
        )
        return ApiResponse.success(responseDto,pagination);*/
        return ApiResponse.success(
            productResponseMapper.toApiResponse(queryProductUseCase.getProducts())
        );
    }
     */

    // 상위 상품 목록 3개를 조회
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