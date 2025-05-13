package scope.commerce.user.api.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.coupon.api.dto.response.CouponApiResponse
import scope.commerce.user.api.dto.response.UserCouponApiResponse
import scope.commerce.user.application.mapper.UserCouponResponseMapper
import scope.commerce.user.application.usecase.QueryUserCouponUseCase

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userCouponResponseMapper: UserCouponResponseMapper,
    private val queryUserCouponUseCase: QueryUserCouponUseCase
) {
    // 발급받은 쿠폰 목록을 조회한다
    @Operation(summary = "유저 쿠폰 목록 조회 API")
    @GetMapping("{userId}/coupons")
    fun getIssuedCoupons(
        @PathVariable userId : Long,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ApiResponse<List<UserCouponApiResponse.Coupon>> {
        // TODO : couponService.getIssuedCoupons(userId);
        // coupon 도메인에서 조회의 책임을 가져야하나 ?
        val response = queryUserCouponUseCase.getUserCoupons(userId,page,size);
        return ApiResponse.success(
            content = userCouponResponseMapper.toApiResponse(response),
            pagination = userCouponResponseMapper.toPagination(response)
        )
    }
}