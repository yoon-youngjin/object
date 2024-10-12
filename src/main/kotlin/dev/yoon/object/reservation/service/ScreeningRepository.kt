package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.Screening

interface ScreeningRepository {
    fun find(id: Long): Screening?

    fun insert(screening: Screening)
}