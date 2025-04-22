package scope.commerce.domain.order.infra.entity;

import jakarta.persistence.*
import scope.commerce.domain.bucket.infra.entity.BucketProduct
import scope.commerce.domain.product.infra.entity.Product
import java.time.LocalDateTime

@Entity
@Table(name = "order_product")
class OrderProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,
    @ManyToOne
    @JoinColumn(name = "bucket_product_id", nullable = true)
    var bucketProduct: BucketProduct?=null,
    var quantity: Int,
    var unitPrice: Int,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
