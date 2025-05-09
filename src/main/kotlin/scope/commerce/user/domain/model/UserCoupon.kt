package scope.commerce.user.domain.model

import java.time.LocalDate

data class UserCoupon(
    val id: Long = 0,
    val userId: Long,
    val couponId: Long,
    val issuedAt: LocalDate = LocalDate.now(),
    private var used: Boolean = false
) {
    fun markUsed() {
        used = true
    }
}
