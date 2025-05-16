package scope.commerce.point.domain.model

data class Point(
    val id: Long?,
    val userId: Long,
    var amount: Long
) {
    fun charge(amountToAdd: Long) {
        require(amountToAdd >= 1) { "충전 금액은 1 이상이어야 합니다." }
        amount += amountToAdd
    }

    fun use(amountToUse: Long) {
        require(amountToUse <= amount) { "잔액이 부족합니다." }
        amount -= amountToUse
    }
}