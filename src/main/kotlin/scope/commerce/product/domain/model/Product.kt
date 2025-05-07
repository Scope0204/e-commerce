package scope.commerce.product.domain.model

data class Product(
    val id: Long,
    val name: String,
    val price: Long,
    val quantity: Long
)