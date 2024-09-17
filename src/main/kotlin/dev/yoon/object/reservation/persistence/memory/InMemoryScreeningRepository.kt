package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.Screening
import dev.yoon.`object`.reservation.persistence.ScreeningRepository

class InMemoryScreeningRepository : ScreeningRepository, InMemoryRepository<Screening>() {
    override fun getScreening(screeningId: Long): Screening {
        return findOne { it.id == screeningId }
    }
}
