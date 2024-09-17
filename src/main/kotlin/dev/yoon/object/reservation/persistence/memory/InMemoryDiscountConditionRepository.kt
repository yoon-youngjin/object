package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.DiscountCondition
import dev.yoon.`object`.reservation.persistence.DiscountConditionRepository

class InMemoryDiscountConditionRepository : DiscountConditionRepository, InMemoryRepository<DiscountCondition>() {
    override fun getDiscountConditions(policyId: Long): List<DiscountCondition> {
        return findMany { it.policyId == policyId }
    }
}
