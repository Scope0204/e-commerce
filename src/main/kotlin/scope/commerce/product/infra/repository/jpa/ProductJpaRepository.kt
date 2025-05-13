package scope.commerce.product.infra.repository.jpa

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.product.infra.entity.ProductEntity
import java.util.*

interface ProductJpaRepository : JpaRepository<ProductEntity, Long> {
    @EntityGraph(attributePaths = ["stock"])
    override fun findById(id: Long): Optional<ProductEntity>

    @EntityGraph(attributePaths = ["stock"])
    override fun findAll(pageable: Pageable): Page<ProductEntity>
}
