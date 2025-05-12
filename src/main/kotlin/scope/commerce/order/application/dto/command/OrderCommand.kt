package scope.commerce.order.application.dto.command

import scope.commerce.order.api.dto.request.OrderApiRequest

data class OrderCommand(
    val userId: Long,
    val fromBucket: Boolean,
    val couponId: Long?,
    val products: List<OrderApiRequest.Create.Product>
)