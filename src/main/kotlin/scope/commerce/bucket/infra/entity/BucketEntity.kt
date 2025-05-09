package scope.commerce.bucket.infra.entity

import jakarta.persistence.*
import scope.commerce.user.infra.entity.UserEntity
import java.time.LocalDateTime

@Entity
@Table(name = "bucket")
class BucketEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var userEntity: UserEntity,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)