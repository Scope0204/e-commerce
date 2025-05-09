package scope.commerce.user.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.user.infra.entity.UserEntity

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
}
