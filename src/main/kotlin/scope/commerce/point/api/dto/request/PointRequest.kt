package scope.commerce.point.api.dto.request

class PointRequest {
    data class Charge(
        val userId: Long,
        val amount: Long,
    )
}