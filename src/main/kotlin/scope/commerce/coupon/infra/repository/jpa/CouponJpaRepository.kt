package scope.commerce.coupon.infra.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import scope.commerce.coupon.infra.entity.CouponEntity

interface CouponJpaRepository : JpaRepository<CouponEntity, Long>