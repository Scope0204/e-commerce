package scope.commerce.point.application.dto.response

data class PointGetResponse(
    override val userId: Long,
    override val currentAmount: Long
) : PointResult

