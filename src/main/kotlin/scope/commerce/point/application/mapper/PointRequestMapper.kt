package scope.commerce.point.application.mapper

import org.springframework.stereotype.Component
import scope.commerce.point.api.dto.request.PointApiRequest
import scope.commerce.point.application.dto.command.ChargePointCommand

@Component
class PointRequestMapper {
    fun toChargeCommand(request: PointApiRequest.Charge): ChargePointCommand {
        return ChargePointCommand(
            userId = request.userId,
            amount = request.amount
        )
    }
}