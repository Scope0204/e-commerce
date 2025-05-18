package scope.commerce.product.infra.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import scope.commerce.common.infra.entity.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "product_stock")
class ProductStockEntity(
    productEntity: ProductEntity,
    quantity: Long,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    var productEntity: ProductEntity = productEntity
        protected set

    @Column(nullable = false)
    var quantity: Long = quantity
        protected set
}
