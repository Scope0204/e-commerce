package scope.commerce.product.domain.model

class ProductStock(
    val id: Long?,
    val productId: Long,
    val quantity: Long
) {
    fun validateEnough(requestedQuantity: Long) {
        require(requestedQuantity > 0) { "주문 수량은 0보다 커야 합니다." }
        if (quantity < requestedQuantity) {
            throw IllegalStateException("재고가 부족합니다. (보유: $quantity, 요청: $requestedQuantity)")
        }
    }

    fun decrease(requestedQuantity: Long): ProductStock {
        validateEnough(requestedQuantity)
        return ProductStock(
            id = this.id,
            productId = this.productId,
            quantity = this.quantity - requestedQuantity
        )
    }

    fun increase(quantityToAdd: Long): ProductStock {
        require(quantityToAdd > 0)
        return ProductStock(
            id = this.id,
            productId = this.productId,
            quantity = this.quantity + quantityToAdd
        )
    }
}