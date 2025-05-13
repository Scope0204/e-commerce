package scope.commerce.order.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.order.domain.model.Order
import scope.commerce.order.domain.repository.OrderRepository
import scope.commerce.order.infra.entity.OrderProductEntity
import scope.commerce.order.infra.mapper.OrderMapper
import scope.commerce.order.infra.repository.jpa.OrderJpaRepository
import scope.commerce.product.infra.repository.jpa.ProductJpaRepository
import scope.commerce.user.infra.repository.jpa.UserCouponJpaRepository
import scope.commerce.user.infra.repository.jpa.UserJpaRepository

@Repository
class OrderRepositoryImpl(
    private val orderMapper: OrderMapper,
    private val userJpaRepository: UserJpaRepository,
    private val userCouponJpaRepository: UserCouponJpaRepository,
    private val orderJpaRepository: OrderJpaRepository,
    private val productJpaRepository: ProductJpaRepository,
) : OrderRepository {

    override fun save(order: Order): Order {
        val userEntity = userJpaRepository.getReferenceById(order.userId)
        val orderEntity = orderMapper.toOrderEntity(order, userEntity)

        // userCoupon 주입
        order.userCouponId?.let {
            val couponEntity = userCouponJpaRepository.getReferenceById(it)
            orderEntity.userCouponEntity = couponEntity
        }
        // orderProduct 주입
        order.products.map { orderProduct ->
            val productEntity = productJpaRepository.getReferenceById(orderProduct.productId)
            val orderProductEntity = OrderProductEntity(
                orderEntity = orderEntity,
                productEntity = productEntity,
                quantity = orderProduct.quantity,
                unitPrice = orderProduct.unitPrice
            )
            orderEntity.addOrderProduct(orderProductEntity)
        }

        val savedEntity = orderJpaRepository.save(orderEntity)
        return orderMapper.toOrder(savedEntity)
    }

    override fun findById(orderId: Long): Order {
        return orderMapper.toOrder(orderJpaRepository.findById(orderId)
            .orElseThrow { IllegalArgumentException("주문 정보를 찾을 수 없습니다. orderId=$orderId") }
        )
    }
}
