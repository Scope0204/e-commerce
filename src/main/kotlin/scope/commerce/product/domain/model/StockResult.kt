package scope.commerce.product.domain.model

enum class StockResult {
    SUCCESS,
    TOTAL_LIMIT,
    // MEMBER_LIMIT // 회원 별 제품 수량 제어가 필요한 경우 추가.
}