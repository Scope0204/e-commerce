package scope.commerce.common.api.controller

import org.springframework.web.bind.annotation.*
import scope.commerce.bucket.api.dto.request.BucketRequest
import scope.commerce.bucket.api.dto.response.BucketResponse
import scope.commerce.common.api.response.ApiResponse
import scope.commerce.coupon.application.dto.CouponServiceDto
import scope.commerce.coupon.api.dto.request.CouponRequest
import scope.commerce.coupon.api.dto.response.CouponResponse
import scope.commerce.order.api.dto.request.OrderRequest
import scope.commerce.order.api.dto.response.OrderResponse
import scope.commerce.payment.api.dto.request.PaymentRequest
import scope.commerce.payment.api.dto.response.PaymentResponse
import scope.commerce.product.application.dto.ProductServiceDto
import scope.commerce.product.application.dto.RankedProductServiceDto
import scope.commerce.product.api.dto.response.ProductResponse
import scope.commerce.bucket.application.dto.BucketServiceDto
import scope.commerce.order.application.dto.OrderServiceDto
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

    // 단건 상품을 조회한다
    @GetMapping("/products/{productId}")
    fun getProduct (
        @PathVariable productId : Long,
    ): ApiResponse<ProductResponse.Product> {
        // TODO : productService.search(productId);
        val responseDto = ProductResponse.Product.from(ProductServiceDto(productId,"oreo", 10000, 10));
        return ApiResponse.success(responseDto);
    }

    // 상품 목록을 조회한다
    @GetMapping("/products")
    fun getProducts (
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ApiResponse<List<ProductResponse.Product>> {
        // TODO : productService.searchList(productId, page, size);
        val mockProducts = listOf(
            ProductServiceDto(1, "oreo", 10000, 10),
            ProductServiceDto(2, "chips", 5000, 20),
            ProductServiceDto(3, "cola", 2000, 15)
        )
        val responseDto = mockProducts.map { ProductResponse.Product.from(it)};
        // 페이징 정보 생성
        val pagination = ApiResponse.Pagination(
            page = page,
            size = size,
            totalElements = 20, // 예시. 실제로는 서비스에서 토탈 갯수를 제공.
            totalPages = ceil((20 / size).toDouble())
        )
        return ApiResponse.success(responseDto,pagination);
    }

    // 상위 상품 목록 3개를 조회한다
    @GetMapping("/products/ranking")
    fun getRankedProducts (
    ): ApiResponse<List<ProductResponse.RankedProduct>> {
        val mockRankedProducts = listOf(
            RankedProductServiceDto(1, "oreo", 10000, 10),
            RankedProductServiceDto(2, "chips", 5000, 20),
            RankedProductServiceDto(3, "cola", 2000, 15)
        )
        val responseDto = mockRankedProducts.map { ProductResponse.RankedProduct.from(it) }
        return ApiResponse.success(responseDto);
    }

    // 선착순 쿠폰을 발급한다
    @PostMapping("/coupons/issue")
    fun issuedCoupon(
        @RequestBody couponRequest : CouponRequest.Issue
    ): ApiResponse<CouponResponse.Coupon> {
        // TODO : couponService.issue(couponId);
        val responseDto = CouponResponse.Coupon
            .from(
                CouponServiceDto(couponRequest.couponId,
                "10% 할인 쿠폰",
                "RATE",
                10,
                5000,
                100,
                99,
                LocalDate.now().plusWeeks(1),
                LocalDate.now())
            );
        return ApiResponse.success(responseDto);
    }
    // 발급받은 쿠폰 목록을 조회한다
    @GetMapping("/coupons/{userId}")
    fun getIssuedCoupons(
        @PathVariable userId : Long,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ApiResponse<List<CouponResponse.Coupon>> {
        // TODO : couponService.getIssuedCoupons(userId);
        val mockCoupons = listOf(
            CouponServiceDto(1, "10% 할인 쿠폰", "PERCENTAGE",10,5000,100,99,LocalDate.now().plusWeeks(1),
                LocalDate.now()),
            CouponServiceDto(2, "1000원 할인 쿠폰", "FIXED",1000,1000,100,99,LocalDate.now().plusWeeks(1),
                LocalDate.now())
        );
        val responseDto = mockCoupons.map { CouponResponse.Coupon.from(it) };
        val pagination = ApiResponse.Pagination(
            page = page,
            size = size,
            totalElements = 20, // 예시. 실제로는 서비스에서 토탈 갯수를 제공.
            totalPages = ceil((20 / size).toDouble())
        )
        return ApiResponse.success(responseDto,pagination);
    }

    // 주문을 요청한다
    @PostMapping("/orders")
    fun createOrder(
        @RequestBody request: OrderRequest.Create
    ): ApiResponse<OrderResponse.Summary> {
        // TODO: orderService.createOrder(request.userId, request.products, request.couponId, request.fromBucket)
        val responseDto = OrderResponse.Summary.from(
            OrderServiceDto(
                orderId = 1234,
                totalAmount = 30000,
                discountAmount = 3000,
                finalAmount = 27000,
                orderedAt = LocalDateTime.now()
            )
        )
        return ApiResponse.success(responseDto)
    }

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