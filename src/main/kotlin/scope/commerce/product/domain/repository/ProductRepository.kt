package scope.commerce.product.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import scope.commerce.product.domain.model.Product

interface ProductRepository {
    fun findById(productId: Long): Product
    fun findAll(pageable: Pageable): Page<Product>
    fun save(product: Product)
    fun saveAll(products: List<Product>): List<Product>
    fun deleteAll()
}
