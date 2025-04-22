package scope.commerce.api.point.dto.response

import scope.commerce.application.point.dto.PointServiceDto

class PointResponse {
    data class Detail(
        val userId: Long,
        val currentAmount: Long,
    ) {
        companion object {
            fun from(serviceDto: PointServiceDto): Detail =
                Detail(
                    userId = serviceDto.userId,
                    currentAmount = serviceDto.currentAmount,
                )
        }
    }
}