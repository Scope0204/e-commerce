package scope.commerce.bucket.api.dto.response

import scope.commerce.bucket.application.dto.BucketServiceDto
import java.time.LocalDateTime

class BucketResponse {
    data class Item(
        val bucketProductId: Long,
        val productId: Long,
        val quantity: Int,
        val unitPrice: Int,
        val addedAt: LocalDateTime
    ) {
        companion object {
            fun from(dto: BucketServiceDto): Item =
                Item(
                    bucketProductId = dto.bucketProductId,
                    productId = dto.productId,
                    quantity = dto.quantity,
                    unitPrice = dto.unitPrice,
                    addedAt = dto.addedAt
                )
        }
    }
}
