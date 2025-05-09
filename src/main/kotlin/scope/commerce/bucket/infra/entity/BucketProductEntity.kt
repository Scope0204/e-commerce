package scope.commerce.bucket.infra.entity;

import jakarta.persistence.*
import scope.commerce.product.infra.entity.ProductEntity

@Entity
@Table(name = "bucket_product")
class BucketProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "bucket_id")
    var bucketEntity: BucketEntity,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var productEntity: ProductEntity,
    var quantity: Long,
    var unitPrice: Long
)
