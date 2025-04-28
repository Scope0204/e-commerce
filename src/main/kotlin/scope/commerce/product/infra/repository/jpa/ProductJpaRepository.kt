package scope.commerce.product.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.product.infra.entity.ProductEntity

interface ProductJpaRepository : JpaRepository<ProductEntity, Long>
