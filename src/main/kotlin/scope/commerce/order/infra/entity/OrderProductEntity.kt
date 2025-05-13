package scope.commerce.order.infra.entity;

import jakarta.persistence.*
import scope.commerce.bucket.infra.entity.BucketProductEntity
import scope.commerce.common.infra.entity.BaseEntity
import scope.commerce.product.infra.entity.ProductEntity

@Entity
@Table(name = "order_product")
class OrderProductEntity(
    orderEntity: OrderEntity,
    productEntity: ProductEntity,
    quantity: Long,
    unitPrice: Long,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var orderEntity: OrderEntity = orderEntity
        internal set

    @ManyToOne
    @JoinColumn(name = "product_id")
    var productEntity: ProductEntity = productEntity
        protected set

    @ManyToOne
    @JoinColumn(name = "bucket_product_id", nullable = true)
    var bucketProductEntity: BucketProductEntity? = null
        protected set

    var quantity: Long = quantity
        protected set

    var unitPrice: Long = unitPrice
        protected set

    init {
        orderEntity.addOrderProduct(this) // 양방향 연관관계 자동 설정
    }
}
