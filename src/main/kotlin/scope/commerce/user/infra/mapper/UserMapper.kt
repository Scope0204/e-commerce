package scope.commerce.user.infra.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import scope.commerce.user.infra.entity.UserEntity
import scope.commerce.user.domain.model.User

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toUser(entity: UserEntity): User
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toUserEntity(domain: User): UserEntity

    fun toUsers(entities: List<UserEntity>): List<User>
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    fun toUserEntities(users: List<User>): List<UserEntity>
}