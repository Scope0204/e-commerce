package scope.commerce.point.application.usecase

import org.springframework.stereotype.Service
import scope.commerce.point.application.dto.response.PointChargeResponse
import scope.commerce.point.application.dto.command.ChargePointCommand
import scope.commerce.point.domain.service.PointService

@Service
class ChargePointUseCase(
    private val pointService: PointService
) {
    fun execute(command: ChargePointCommand): PointChargeResponse {
        val chargePoint = pointService.charge(command.userId, command.amount)
        return PointChargeResponse (
            userId = chargePoint.userId,
            currentAmount = chargePoint.amount
        )
    }
}


