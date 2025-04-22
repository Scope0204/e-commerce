package scope.commerce.api.bucket.dto.request

class BucketRequest {
    data class Add(
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val option: String? = null
    )
}
