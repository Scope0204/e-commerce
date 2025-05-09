package scope.commerce.user.domain.repository

import scope.commerce.user.domain.model.User

interface UserRepository {
    fun findById(userId: Long): User
}