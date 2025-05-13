package scope.commerce.point.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.point.application.dto.response.PointChargeResponse
import scope.commerce.point.application.dto.command.ChargePointCommand
import scope.commerce.point.domain.service.PointService

@Service
class ChargePointUseCase(
    private val pointService: PointService
) {
    @Transactional
    fun execute(command: ChargePointCommand): PointChargeResponse {
        val chargePoint = pointService.charge(command.userId, command.amount)
        return PointChargeResponse (
            userId = chargePoint.userId,
            currentAmount = chargePoint.amount
        )
    }
}


