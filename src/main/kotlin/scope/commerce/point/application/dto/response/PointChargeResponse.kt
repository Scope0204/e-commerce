package scope.commerce.point.application.dto.response

data class PointChargeResponse(
    override val userId: Long,
    override val currentAmount: Long
) : PointResult
