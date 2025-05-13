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
    val id: Long? = null

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var price: Long = price
        protected set

    @OneToOne(mappedBy = "productEntity", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = false)
    var stock: ProductStockEntity? = null
        protected set
}


