package dev.yoon.`object`.reservation.persistence

import dev.yoon.`object`.reservation.domain.Reservation

interface ReservationRepository {
    fun insert(reservation: Reservation)
}