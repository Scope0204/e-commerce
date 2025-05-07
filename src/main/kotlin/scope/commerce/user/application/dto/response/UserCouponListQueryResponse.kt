package scope.commerce.user.application.dto.response

data class UserCouponListQueryResponse(
    val userCoupons: List<UserCouponResponse>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int
)