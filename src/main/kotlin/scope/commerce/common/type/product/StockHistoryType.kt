package scope.commerce.common.type.product

enum class StockHistoryType(
    val description: String
) {
    PLUS("재고사용량 증가"),
    MINUS("재고사용량 감소");
}