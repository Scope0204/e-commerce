package scope.commerce.product.domain.model

import scope.commerce.common.type.product.ProductStatus

data class Product(
    val id: Long?,
    val name: String,
    val price: Long,
    val quantity: Long,
    val status: ProductStatus
) {

    // 수량에 따른 주문 관리 여부
    fun isAvailable(requestedQuantity: Long): Boolean {
        return quantity >= requestedQuantity
    }

    fun validatePurchasable(requestedQuantity: Long) {
        require(requestedQuantity > 0) { "주문 수량은 0보다 커야 합니다." }
        if (!isAvailable(requestedQuantity)) {
            throw IllegalStateException("재고가 부족합니다. (보유: $quantity, 요청: $requestedQuantity)")
        }
    }

    // 할인 가격 계산
    fun applyDiscount(discountAmount: Long): Long {
        require(discountAmount in 0..price) { "할인 금액이 유효하지 않습니다." }
        return price - discountAmount
    }

    // 상품 재고 만료
    fun updateSoldOut(): Product {
        return this.copy(status = ProductStatus.SOLD_OUT)
    }
}
