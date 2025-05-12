package scope.commerce.order.application.usecase

import org.springframework.stereotype.Service
import scope.commerce.coupon.domain.service.CouponService
import scope.commerce.order.application.dto.command.OrderCommand
import scope.commerce.order.application.dto.response.OrderResponse
import scope.commerce.order.domain.service.OrderService
import scope.commerce.product.domain.service.ProductService
import scope.commerce.user.domain.service.UserService

@Service
class OrderUseCase(
    private val userService: UserService,
    private val productService: ProductService,
    private val couponService: CouponService,
    private val orderService: OrderService
){
    /**
     * 주문을 진행한다.
     * 1. 사용자 ID 회원 인증
     * 2. 장바구니 구매인지 확인
     * 3. 상품 목록을 확인
     * 4. 쿠폰 사용 여부 확인 후 상태 변경(쿠폰 재고 차감)
     * 5. 주문 목록 생성 후 저장
     */
    fun execute(command: OrderCommand): OrderResponse {
        // 사용자 존재 여부 확인
        val user = userService.findById(command.userId)

        // TODO: 장바구니 구매 여부 확인 및 추가적으로 처리하는 로직 추가 필요

        // 상품 목록 확인 (단품도 List 로 받아서 처리)
        val products = command.products.map {
            // 상품 정보 조회
            val product = productService.getProductById(it.productId)
            // 상품 구매 가능 여부 검증( 판매 가능한지 ? 수량이 부족한지 ? 현재는 수량 외에 따로 검증요소가 존재하지 않아 재고 가능 여부만 확인 )
            product.validatePurchasable(it.quantity)
            product
        }

        // 쿠폰이 존재하는 경우 유효성 확인
        val coupon = command.couponId?.let { couponService.validateCoupon(it) }

        // 주문 목록 생성 후 저장
        val order = orderService.createOrder(user, command.fromBucket, products, coupon)
        val orderResult = orderService.saveOrder(order);

        return OrderResponse(
            orderId = orderResult.id,
            userCouponId = coupon?.id,
            totalAmount = orderResult.totalAmount,
            discountAmount = orderResult.discountAmount,
            finalAmount = orderResult.finalAmount,
            orderedAt = orderResult.orderAt
        )
    }
}


