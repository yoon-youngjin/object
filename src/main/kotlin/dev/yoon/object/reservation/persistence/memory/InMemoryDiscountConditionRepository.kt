package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.service.DiscountConditionRepository

class InMemoryDiscountConditionRepository : DiscountConditionRepository, InMemoryRepository<DummyDiscountCondition>() {
    override fun getDiscountConditions(policyId: Long): List<DummyDiscountCondition> {
        return findMany { it.policyId == policyId }
    }
}
