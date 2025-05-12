package scope.commerce.order.api.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.order.api.dto.request.OrderApiRequest
import scope.commerce.order.api.dto.response.OrderApiResponse
import scope.commerce.order.application.mapper.OrderRequestMapper
import scope.commerce.order.application.mapper.OrderResponseMapper
import scope.commerce.order.application.usecase.OrderUseCase
import scope.commerce.point.application.mapper.PointRequestMapper

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderUseCase: OrderUseCase,
    private val orderRequestMapper: OrderRequestMapper,
    private val orderResponseMapper: OrderResponseMapper
) {
    // 주문 요청을 생성한다.
    @Operation(summary = "주문 생성 API")
    @PostMapping("/issue")
    fun createOrder(
        @RequestBody orderRequest : OrderApiRequest.Create
    ): ApiResponse<OrderApiResponse.Summary> {
        val command = orderRequestMapper.toOrderCommand(orderRequest)
        return ApiResponse.success(
            orderResponseMapper.toApiResponse(orderUseCase.execute(command))
        );
    }
}