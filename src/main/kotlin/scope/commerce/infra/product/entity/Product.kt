package scope.commerce.infra.product.entity

import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var name: String,
    var price: Int,
    var quantity: Int
)