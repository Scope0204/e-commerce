package scope.commerce.point.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.point.api.dto.response.PointApiResponse
import scope.commerce.point.api.dto.response.PointResult

@Component
class PointResponseMapper {
    fun toApiResponse(result: PointResult): PointApiResponse.Detail {
        return PointApiResponse.Detail(
            userId = result.userId,
            currentAmount = result.currentAmount
        )
    }
}