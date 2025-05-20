package scope.commerce.product.infra.entity

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity
import scope.commerce.common.type.product.StockHistoryType

@Entity
@Table(name = "stock_history")
class StockHistoryEntity(
    productId: Long,
    orderId: Long,
    userId: Long,
    purchaseNumber: String,
    price: Long,
    type: StockHistoryType
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "product_id", nullable = false)
    var productId: Long = productId
        protected set

    @Column(name = "order_id", nullable = false)
    var orderId: Long = orderId
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: Long = userId
        protected set

    @Column(name = "purchase_number", nullable = false, length = 40)
    var purchaseNumber: String = purchaseNumber
        protected set

    @Column(name = "price", nullable = false)
    var price: Long = price
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    var type: StockHistoryType = type
        protected set
}

