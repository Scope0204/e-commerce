package scope.commerce.coupon.api.dto.request

class CouponApiRequest{
    data class Issue(
        var userId: Long,
        var couponId: Long,
    )
}
