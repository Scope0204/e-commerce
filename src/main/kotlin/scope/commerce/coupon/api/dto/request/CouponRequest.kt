package scope.commerce.coupon.api.dto.request

class CouponRequest{
    data class Issue(
        var userId: Long,
        var couponId: Long,
    )
}
