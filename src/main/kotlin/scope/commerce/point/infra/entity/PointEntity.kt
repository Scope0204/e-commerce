package scope.commerce.point.infra.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import scope.commerce.common.infra.entity.BaseEntity

@Getter
@NoArgsConstructor
@Entity
@Table(name = "point", uniqueConstraints = [UniqueConstraint(columnNames = ["user_id"])])
class PointEntity(
    id: Long?,
    userId: Long,
    amount: Long
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @Column(name = "user_id")
    var userId: Long = userId
        protected set

    var amount: Long = amount
        protected set
}