package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.Reservation
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository {
    fun insert(reservation: Reservation): Reservation
}