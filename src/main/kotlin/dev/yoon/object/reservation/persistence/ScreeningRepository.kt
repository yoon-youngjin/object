package dev.yoon.`object`.reservation.persistence

import dev.yoon.`object`.reservation.domain.Screening

interface ScreeningRepository {
    fun getScreening(screeningId: Long): Screening

    fun insert(screening: Screening)
}