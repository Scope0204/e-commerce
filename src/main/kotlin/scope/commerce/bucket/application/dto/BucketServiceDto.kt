package scope.commerce.bucket.application.dto

import java.time.LocalDateTime

data class BucketServiceDto(
    val bucketProductId: Long,
    val productId: Long,
    val quantity: Int,
    val unitPrice: Int,
    val addedAt: LocalDateTime
)
