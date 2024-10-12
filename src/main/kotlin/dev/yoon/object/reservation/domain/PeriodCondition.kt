package dev.yoon.`object`.reservation.domain

import java.time.DayOfWeek
import java.time.LocalTime

class PeriodCondition(
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime,
    private val endTime: LocalTime,
) : DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean {
        return screening.getStartTime().dayOfWeek.equals(dayOfWeek) &&
                startTime <= screening.getStartTime().toLocalTime() &&
                endTime >= screening.getStartTime().toLocalTime()
    }
}
