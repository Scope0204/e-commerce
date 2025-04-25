package scope.commerce.point.api.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.point.api.dto.request.PointApiRequest
import scope.commerce.point.api.dto.response.PointApiResponse
import scope.commerce.point.application.mapper.PointRequestMapper
import scope.commerce.point.application.mapper.PointResponseMapper
import scope.commerce.point.application.usecase.ChargePointUseCase
import scope.commerce.point.application.usecase.GetPointUseCase

@RestController
@RequestMapping("/api/v1/points")
class PointController(
    private val chargePointUseCase: ChargePointUseCase,
    private val getPointUseCase: GetPointUseCase,
    private val pointRequestMapper: PointRequestMapper,
    private val pointResponseMapper: PointResponseMapper
    ) {

    @PostMapping("/charge")
    @Operation(summary = "잔액 충전 API")
    fun chargePoint(
        @Valid @RequestBody chargeRequest: PointApiRequest.Charge
    ): ApiResponse<PointApiResponse.Detail> {
        val command = pointRequestMapper.toChargeCommand(chargeRequest)
        return ApiResponse.success(
            pointResponseMapper.toApiResponse(chargePointUseCase.execute(command))
        )
    }

    @Operation(summary = "잔액 조회 API")
    @GetMapping("/{userId}")
    fun searchPoint (
        @PathVariable userId : Long,
    ): ApiResponse<PointApiResponse.Detail> {
        return ApiResponse.success(
            pointResponseMapper.toApiResponse(getPointUseCase.execute(userId)))
    }
}