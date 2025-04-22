package scope.commerce.infra.product.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "product_stock")
class ProductStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    val product: Product,
    val quantity: Int,
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
