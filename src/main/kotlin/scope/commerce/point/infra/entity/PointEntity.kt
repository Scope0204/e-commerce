package scope.commerce.point.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "point", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id"])])
class PointEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "user_id")
    var userId: Long,
    var amount: Long
)