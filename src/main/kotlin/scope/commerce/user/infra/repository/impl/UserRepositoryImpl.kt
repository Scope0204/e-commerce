package scope.commerce.user.infra.repository.impl

import org.springframework.stereotype.Repository
import scope.commerce.user.domain.model.User
import scope.commerce.user.domain.repository.UserRepository
import scope.commerce.user.infra.mapper.UserMapper
import scope.commerce.user.infra.repository.jpa.UserJpaRepository

@Repository
class UserRepositoryImpl(
    private val userMapper: UserMapper,
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findById(userId: Long): User {
        return userMapper.toUser(userJpaRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("회원 정보를 찾을 수 없습니다. userId=$userId") }
        )
    }
}