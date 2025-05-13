package scope.commerce.point.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.point.application.dto.response.PointGetResponse
import scope.commerce.point.domain.service.PointService

@Service
class GetPointUseCase (
    private val pointService: PointService
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): PointGetResponse {
        val chargePoint = pointService.getUserPoint(userId)
        return PointGetResponse (
            userId = chargePoint.userId,
            currentAmount =  chargePoint.amount
        );
    }
}