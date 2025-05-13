package scope.commerce.user.domain.repository

import scope.commerce.user.domain.model.User

interface UserRepository {
    fun findById(userId: Long): User
    fun save(user: User): User
    fun saveAll(users: List<User>): List<User>
    fun deleteAll()
}