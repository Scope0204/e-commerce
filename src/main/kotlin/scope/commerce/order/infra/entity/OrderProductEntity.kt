package scope.commerce.order.infra.entity;

import jakarta.persistence.*
import scope.commerce.bucket.infra.entity.BucketProductEntity
import scope.commerce.product.infra.entity.ProductEntity
import java.time.LocalDateTime

@Entity
@Table(name = "order_product")
class OrderProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var productEntity: ProductEntity,
    @ManyToOne
    @JoinColumn(name = "bucket_product_id", nullable = true)
    var bucketProductEntity: BucketProductEntity?=null,
    var quantity: Long,
    var unitPrice: Long,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
