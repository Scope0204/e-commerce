package scope.commerce.bucket.infra.entity;

import jakarta.persistence.*
import scope.commerce.product.infra.entity.Product

@Entity
@Table(name = "bucket_product")
class BucketProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "bucket_id")
    var bucket: Bucket,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,
    var quantity: Int,
    var unitPrice: Int
)
