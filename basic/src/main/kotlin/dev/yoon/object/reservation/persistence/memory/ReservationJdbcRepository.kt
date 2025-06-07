package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.Reservation
import dev.yoon.`object`.reservation.service.ReservationRepository

class ReservationJdbcRepository: ReservationRepository {
    override fun insert(reservation: Reservation) {
        TODO("Not yet implemented")
    }
}