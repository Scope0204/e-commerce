package scope.commerce.product.infra.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "product_stock")
class ProductStockEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    val productEntity: ProductEntity,
    val quantity: Int,
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
