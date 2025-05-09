package scope.commerce.bucket.api.dto.request

class BucketRequest {
    data class Add(
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val option: String? = null
    )
}
