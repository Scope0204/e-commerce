package scope.commerce.point.api.dto.response

class PointApiResponse {
    data class Detail(
        val userId: Long,
        val currentAmount: Long,
    )
}