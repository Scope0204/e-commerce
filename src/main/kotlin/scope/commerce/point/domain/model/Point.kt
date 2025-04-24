package scope.commerce.point.domain.model

data class Point(
    val userId: Long,
    var amount: Long
) {
    fun charge(amountToAdd: Long) {
        require(amountToAdd > 0) { "충전 금액은 0보다 커야 합니다." }
        amount += amountToAdd
    }

    fun use(amountToUse: Long) {
        require(amountToUse <= amount) { "잔액이 부족합니다." }
        amount -= amountToUse
    }
}