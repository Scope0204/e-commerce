package scope.commerce.user.infra.mapper

import org.mapstruct.Mapper
import scope.commerce.user.infra.entity.UserEntity
import scope.commerce.user.domain.model.User

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toUser(entity: UserEntity): User
    fun toUserEntity(domain: User): UserEntity
}