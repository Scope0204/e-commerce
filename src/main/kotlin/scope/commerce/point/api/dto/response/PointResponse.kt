package scope.commerce.point.api.dto.response

import scope.commerce.point.application.dto.PointServiceDto

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