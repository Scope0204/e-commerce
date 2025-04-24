package scope.commerce.point.api.dto.request

import jakarta.validation.constraints.Positive

class PointApiRequest {
    data class Charge(
        val userId: Long,
        @field:Positive(message = "충전 포인트 입력이 필요합니다.")
        val amount: Long,
    )
}