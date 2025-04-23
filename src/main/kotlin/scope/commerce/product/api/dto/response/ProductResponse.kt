package scope.commerce.product.api.dto.response

import scope.commerce.product.application.dto.ProductServiceDto
import scope.commerce.product.application.dto.RankedProductServiceDto

class ProductResponse {
    data class Product (
        val productId: Long,
        val name: String,
        val price: Long,
        val quantity: Int,
    ) {
        companion object {
            fun from(serviceDto: ProductServiceDto): Product =
                Product(
                    productId = serviceDto.productId,
                    name = serviceDto.name,
                    price = serviceDto.price,
                    quantity = serviceDto.quantity
                )
        }
    }

    data class RankedProduct(
        val productId: Long,
        val name: String,
        val price: Long,
        val soldCount: Int
    ) {
        companion object {
            fun from(serviceDto: RankedProductServiceDto): RankedProduct =
                RankedProduct(
                    productId = serviceDto.productId,
                    name = serviceDto.name,
                    price = serviceDto.price,
                    soldCount = serviceDto.soldCount
                )
        }
    }
}