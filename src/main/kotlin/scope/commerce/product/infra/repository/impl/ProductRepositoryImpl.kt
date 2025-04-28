package scope.commerce.product.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.product.domain.model.Product
import scope.commerce.product.domain.repository.ProductRepository
import scope.commerce.product.infra.mapper.ProductMapper
import scope.commerce.product.infra.repository.jpa.ProductJpaRepository

@Repository
class ProductRepositoryImpl(
    private val productMapper: ProductMapper,
    private val productJpaRepository: ProductJpaRepository,
) : ProductRepository {

    override fun findById(productId: Long): Product {
        val productEntity = productJpaRepository.findById(productId)
            .orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다. productId=$productId") }

        return productMapper.toProduct(productEntity)

    }
}