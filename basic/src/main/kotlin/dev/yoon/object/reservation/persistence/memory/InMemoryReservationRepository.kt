package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.Reservation
import dev.yoon.`object`.reservation.service.ReservationRepository

class InMemoryReservationRepository: ReservationRepository, InMemoryRepository<Reservation>()