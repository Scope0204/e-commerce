package scope.commerce.api.coupon.dto.request

class CouponRequest{
    data class Issue(
        var userId: Long,
        var couponId: Long,
    )
}
