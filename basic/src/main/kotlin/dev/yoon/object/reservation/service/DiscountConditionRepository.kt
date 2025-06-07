package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.DiscountCondition

interface DiscountConditionRepository {
    fun getDiscountConditions(policyId: Long): List<DiscountCondition>

    fun insert(dummyDiscountCondition: DiscountCondition)
}