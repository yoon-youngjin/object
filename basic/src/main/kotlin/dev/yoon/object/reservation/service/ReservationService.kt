package dev.yoon.`object`.reservation.service

import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class ReservationService(
    private val transactionTemplate: TransactionTemplate,
    private val customerRepository: CustomerRepository,
    private val screeningRepository: ScreeningRepository,
    private val reservationRepository: ReservationRepository,
) {
    fun reserveScreening(customerId: Long, screeningId: Long, audienceCount: Int) {
        transactionTemplate.execute {
            val customer = customerRepository.find(customerId)!!
            val screening = screeningRepository.find(screeningId)!!

            val reservation = screening.reserve(customer, audienceCount)

            return@execute reservationRepository.insert(reservation)
        }
    }
}