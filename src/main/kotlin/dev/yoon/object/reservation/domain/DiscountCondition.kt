package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.TimeInterval
import java.time.DayOfWeek

data class DiscountCondition(
    val id: Long,
    val policyId: Long,
    val conditionType: ConditionType,
    val dayOfWeek: DayOfWeek,
    val interval: TimeInterval,
    val sequence: Int,
) {
    enum class ConditionType {
        PERIOD_CONDITION, SEQUENCE_CONDITION, COMBINED_CONDITION
    }

    fun isSatisfiedBy(screening: Screening): Boolean {
        if (isPeriodCondition()) {
            if (screening.isPlayedIn(dayOfWeek, interval.startTime, interval.endTime)) return true
        } else if (isSequenceCondition()) {
            if (sequence == screening.sequence) return true
        } else if (isCombinedCondition()) {
            if (sequence == screening.sequence
                && screening.isPlayedIn(dayOfWeek, interval.startTime, interval.endTime)
            ) {
                return true
            }
        }

        return false
    }

    private fun isPeriodCondition(): Boolean {
        return conditionType == ConditionType.PERIOD_CONDITION
    }

    private fun isSequenceCondition(): Boolean {
        return conditionType == ConditionType.SEQUENCE_CONDITION
    }

    private fun isCombinedCondition(): Boolean {
        return conditionType == ConditionType.COMBINED_CONDITION
    }
}
