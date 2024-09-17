package dev.yoon.`object`.reservation.domain

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class Screening(
    val id: Long,
    val movieId: Long,
    val sequence: Int,
    val screeningTime: LocalDateTime,
) {
    fun isPlayedIn(dayOfWeek: DayOfWeek, startTime: LocalTime, endTime: LocalTime): Boolean {
        return screeningTime.dayOfWeek == dayOfWeek
                && (this.screeningTime.toLocalTime() == startTime || this.screeningTime.toLocalTime().isAfter(startTime))
                && (this.screeningTime.toLocalTime() == endTime || this.screeningTime.toLocalTime().isBefore(endTime))
    }
}
