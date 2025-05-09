package scope.commerce.user.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scope.commerce.user.domain.model.User
import scope.commerce.user.domain.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun findById(userId: Long): User {
        return userRepository.findById(userId)
    }
}
