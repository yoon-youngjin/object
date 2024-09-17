package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

data class DiscountPolicy(
    val id: Long,
    val movieId: Long,
    val policyType: PolicyType,
    val amount: Money,
    val percent: Double,
    val conditions: List<DiscountCondition>,
) {
    enum class PolicyType {
        PERCENT_POLICY, AMOUNT_POLICY
    }

    fun calculateDiscount(movie: Movie): Money {
        if (isAmountPolicy()) {
            return amount
        } else if (isPercentPolicy()) {
            return movie.fee.times(percent)
        }

        return Money.ZERO
    }

    fun existsSatisfiedDiscountCondition(screening: Screening): Boolean {
        for (condition in conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return true
            }
        }

        return false
    }

    private fun isAmountPolicy(): Boolean {
        return PolicyType.AMOUNT_POLICY == this.policyType
    }

    private fun isPercentPolicy(): Boolean {
        return PolicyType.PERCENT_POLICY == this.policyType
    }
}
