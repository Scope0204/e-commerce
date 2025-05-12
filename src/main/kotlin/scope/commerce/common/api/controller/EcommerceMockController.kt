package scope.commerce.common.api.controller

import org.springframework.web.bind.annotation.*
import scope.commerce.bucket.api.dto.request.BucketRequest
import scope.commerce.bucket.api.dto.response.BucketResponse
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.order.api.dto.request.OrderApiRequest
import scope.commerce.payment.api.dto.request.PaymentRequest
import scope.commerce.payment.api.dto.response.PaymentResponse
import scope.commerce.bucket.application.dto.BucketServiceDto
import scope.commerce.order.application.dto.response.OrderResponse
import scope.commerce.payment.application.dto.PaymentServiceDto
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.ceil

/**
 * mock api controller
 * - 실제 api 는 각 도메인 컨트롤러에서 담당하도록 한다.
 * - 구현 완료 시 제거.
 */
@RestController
@RequestMapping("/api/v1/mock")
class EcommerceMockController {

    // 결제를 진행한다
    @PostMapping("/payments")
    fun makePayment(
        @RequestBody request: PaymentRequest.Execute
    ): ApiResponse<PaymentResponse.Detail> {
        // TODO: paymentService.pay(request.userId, request.orderId)
        val responseDto = PaymentResponse.Detail.from(
            PaymentServiceDto(
                paymentId = 4567,
                paymentAmount = 20000,
                paymentStatus = "SUCCESS",
                paidAt = LocalDate.now().atStartOfDay()
            )
        )
        return ApiResponse.success(responseDto)
    }

    // 관심 상품을 장바구니에 추가한다
    @PostMapping("/buckets")
    fun addToBucket(
        @RequestBody request: BucketRequest.Add
    ): ApiResponse<BucketResponse.Item> {
        // TODO: bucketService.add(request.userId, request.productId, request.quantity, request.option)
        val responseDto = BucketResponse.Item.from(
            BucketServiceDto(
                bucketProductId = 555,
                productId = request.productId,
                quantity = request.quantity,
                unitPrice = 15000,
                addedAt = LocalDate.now().atStartOfDay()
            )
        )
        return ApiResponse.success(responseDto)
    }

    // 장바구니에 추가한 상품들을 조회한다
    @GetMapping("/buckets/{userId}")
    fun getBucketItems(
        @PathVariable userId: Long,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ApiResponse<List<BucketResponse.Item>> {
        // TODO: bucketService.getItems(userId)
        val mockItems = listOf(
            BucketServiceDto(555, 101, 2, 15000, LocalDate.now().atStartOfDay()),
            BucketServiceDto(556, 102, 1, 22000, LocalDate.now().atStartOfDay())
        )
        val pagination = ApiResponse.Pagination(
            page = page,
            size = size,
            totalElements = 20, // 예시. 실제로는 서비스에서 토탈 갯수를 제공.
            totalPages = ceil((20 / size).toDouble())
        )
        val responseDto = mockItems.map { BucketResponse.Item.from(it) }

        return ApiResponse.success(responseDto, pagination)
    }

}