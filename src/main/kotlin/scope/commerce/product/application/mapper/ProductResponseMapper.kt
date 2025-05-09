package scope.commerce.product.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.product.api.dto.response.ProductApiResponse
import scope.commerce.product.application.dto.response.ProductListQueryResponse
import scope.commerce.product.application.dto.response.ProductResponse

@Component
class ProductResponseMapper {
    fun toApiResponse(response: ProductResponse): ProductApiResponse.Product {
        return ProductApiResponse.Product(
            productId = response.productId,
            name = response.name,
            price = response.price,
            quantity = response.quantity
        )
    }

    fun toApiResponse(response: ProductListQueryResponse): List<ProductApiResponse.Product> {
        return response.products.map { toApiResponse(it) }
    }

    fun toPagination(response: ProductListQueryResponse): ApiResponse.Pagination {
        return ApiResponse.Pagination(
            page = response.page,
            size = response.size,
            totalElements = response.totalElements,
            totalPages = response.totalPages.toDouble()
        )
    }
}