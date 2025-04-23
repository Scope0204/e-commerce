package scope.commerce.point.infra.entity

import jakarta.persistence.*
import scope.commerce.user.infra.entity.User

@Entity
class Point(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User,
    var amount: Int
)