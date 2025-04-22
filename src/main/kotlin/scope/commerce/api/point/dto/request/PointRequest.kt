package scope.commerce.api.point.dto.request

class PointRequest {
    data class Charge(
        val userId: Long,
        val amount: Long,
    )
}