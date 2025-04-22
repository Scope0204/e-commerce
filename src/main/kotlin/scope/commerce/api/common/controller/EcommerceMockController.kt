package scope.commerce.api.common.controller

import org.springframework.web.bind.annotation.*
import scope.commerce.api.bucket.dto.request.BucketRequest
import scope.commerce.api.bucket.dto.response.BucketResponse
import scope.commerce.api.common.dto.ApiResponse
import scope.commerce.application.coupon.dto.CouponServiceDto
import scope.commerce.api.coupon.dto.request.CouponRequest
import scope.commerce.api.coupon.dto.response.CouponResponse
import scope.commerce.api.order.dto.request.OrderRequest
import scope.commerce.api.order.dto.response.OrderResponse
import scope.commerce.api.payment.dto.request.PaymentRequest
import scope.commerce.api.payment.dto.response.PaymentResponse
import scope.commerce.application.point.dto.PointServiceDto
import scope.commerce.api.point.dto.request.PointRequest
import scope.commerce.api.point.dto.response.PointResponse
import scope.commerce.application.product.dto.ProductServiceDto
import scope.commerce.application.product.dto.RankedProductServiceDto
import scope.commerce.api.product.dto.response.ProductResponse
import scope.commerce.application.bucket.dto.BucketServiceDto
import scope.commerce.application.order.dto.OrderServiceDto
import scope.commerce.application.payment.dto.PaymentServiceDto
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.ceil

/**
 * mock api controller
 * - 실제 api 는 각 도메인 컨트롤러에서 담당하도록 한다.
 */

@RestController
@RequestMapping("/api/v1/mock")
class EcommerceMockController {

    // 잔액을 충전한다
    @PostMapping("/points/charge")
    fun chargePoint (
        @RequestBody chargeRequest : PointRequest.Charge
    ): ApiResponse<PointResponse.Detail> {
        // TODO : pointService.charge(chargeRequest.userId, chargeRequest.amount);
        val responseDto = PointResponse.Detail.from(PointServiceDto(chargeRequest.userId,chargeRequest.amount+2000));
        return ApiResponse.success(responseDto);
    }

    // 잔액을 조회한다
    @GetMapping("/points/{userId}")
    fun getPoint (
        @PathVariable userId : Long,
    ): ApiResponse<PointResponse.Detail> {
        // TODO : pointService.search(userId);
        val responseDto = PointResponse.Detail.from(PointServiceDto(1,2000));
        return ApiResponse.success(responseDto);
    }

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