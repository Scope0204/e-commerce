package scope.commerce.product.fixture

import org.springframework.stereotype.Component
import scope.commerce.product.domain.model.Product
import scope.commerce.product.domain.model.ProductStock
import scope.commerce.product.domain.repository.ProductRepository
import scope.commerce.product.domain.repository.ProductStockRepository

@Component
class ProductFixture(
    private val productRepository: ProductRepository,
    private val productStockRepository: ProductStockRepository
) {
    private val savedProducts = mutableListOf<Product>()

    /**
     * product + ProductStock 개별 저장
     */
    fun prepareProducts(count: Int) {
        val products = (1..count).map { i ->
            Product(
                id = null,
                name = "product-$i",
                price = 1000L * i,
                quantity = 10L * i
            )
        }

        val persistedProducts = productRepository.saveAll(products)
        savedProducts.clear()
        savedProducts.addAll(persistedProducts)

        val productStocks = persistedProducts.map { product ->
            productStockRepository.save(
                ProductStock(
                    id = null,
                    productId = product.id!!,
                    quantity = product.quantity
                )
            )
        }
    }

    fun getAllSavedProducts(): List<Product> = savedProducts
}
