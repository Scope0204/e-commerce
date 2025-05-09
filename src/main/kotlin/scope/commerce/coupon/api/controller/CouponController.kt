package scope.commerce.coupon.api.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.coupon.api.dto.request.CouponApiRequest
import scope.commerce.coupon.api.dto.response.CouponApiResponse
import scope.commerce.coupon.application.mapper.CouponRequestMapper
import scope.commerce.coupon.application.mapper.CouponResponseMapper
import scope.commerce.coupon.application.usecase.IssueCouponUseCase

@RestController
@RequestMapping("/api/v1/coupons")
class CouponController(
    private val issueCouponUseCase: IssueCouponUseCase,
    private val couponRequestMapper: CouponRequestMapper,
    private val couponResponseMapper: CouponResponseMapper

) {
    // 선착순 쿠폰을 발급한다
    @Operation(summary = "선착순 쿠폰 발급 API")
    @PostMapping("/issue")
    fun issuedCoupon(
        @RequestBody couponRequest : CouponApiRequest.Issue
    ): ApiResponse<CouponApiResponse.Coupon> {
        val command = couponRequestMapper.toIssueCouponCommand(couponRequest)
        return ApiResponse.success(
            couponResponseMapper.toApiResponse(issueCouponUseCase.issueCoupon(command))
        );
    }
}