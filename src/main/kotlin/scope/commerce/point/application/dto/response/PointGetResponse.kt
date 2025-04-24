package scope.commerce.point.application.dto.response

import scope.commerce.point.api.dto.response.PointResult

data class PointGetResponse(
    override val userId: Long,
    override val currentAmount: Long
) : PointResult

