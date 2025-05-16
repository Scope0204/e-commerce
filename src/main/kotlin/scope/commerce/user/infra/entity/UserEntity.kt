package scope.commerce.user.infra.entity

import jakarta.persistence.*
import scope.commerce.common.infra.entity.BaseEntity

@Entity
@Table(name = "users")
class UserEntity(
    name: String,
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var name: String = name
        protected set
}