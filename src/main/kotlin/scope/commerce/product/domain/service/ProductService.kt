package scope.commerce.product.domain.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import scope.commerce.product.domain.model.Product
import scope.commerce.product.domain.repository.ProductRepository

@Service
class ProductService(
    private val productRepository: ProductRepository,
    ) {
    // 상품 정보 조회
    fun getProductById(productId: Long): Product {
        val product = productRepository.findById(productId)

        return Product(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity
        )
    }

    // 상품 목록 조회
    fun getProducts(page: Int, size: Int): Page<Product> {
        val pageable = PageRequest.of(page - 1, size)
        return productRepository.findAll(pageable)
    }
}
