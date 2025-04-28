package scope.commerce.product.infra.entity

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity

@Entity
@Table(name = "product")
class ProductEntity(
    name: String,
    price: Long,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var price: Long = price
        protected set
}


