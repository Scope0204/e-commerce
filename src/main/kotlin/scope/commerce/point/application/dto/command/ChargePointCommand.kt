package scope.commerce.point.application.dto.command

data class ChargePointCommand(
    val userId: Long,
    val amount: Long
) {
    init {
        require(amount > 0) { "최소 충전 포인트는 1 이상입니다." }
    }
}