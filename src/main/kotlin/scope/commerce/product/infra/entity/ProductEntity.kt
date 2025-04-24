package scope.commerce.product.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var price: Long,
    var quantity: Long
)