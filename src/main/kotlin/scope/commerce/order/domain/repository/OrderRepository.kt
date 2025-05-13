package scope.commerce.order.domain.repository

import scope.commerce.order.domain.model.Order

interface OrderRepository {
    fun save(order: Order): Order
    fun findById(orderId: Long): Order
}