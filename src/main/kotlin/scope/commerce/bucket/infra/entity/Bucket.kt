package scope.commerce.bucket.infra.entity

import jakarta.persistence.*
import scope.commerce.user.infra.entity.User
import java.time.LocalDateTime

@Entity
class Bucket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)