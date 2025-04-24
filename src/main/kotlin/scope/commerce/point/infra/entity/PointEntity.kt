package scope.commerce.point.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "point")
class PointEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @Column(name = "user_id")
    var userId: Long,
    var amount: Long
)