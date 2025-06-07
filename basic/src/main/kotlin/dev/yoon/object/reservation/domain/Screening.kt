package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money
import java.time.LocalDateTime

data class Screening(
    val movie: Movie,
    val sequence: Int,
    val whenScreened: LocalDateTime,
) {
    fun reserve(customer: Customer, audienceCount: Int): Reservation {
        val fee = movie.calculateFee(this).times(audienceCount.toDouble())
        return Reservation(customer, this, audienceCount, fee)
    }

    fun getFixedFee(): Money {
        return movie.fee
    }

    fun isSequence(sequence: Int): Boolean {
        return this.sequence == sequence
    }

    fun getStartTime(): LocalDateTime {
        return whenScreened
    }
}
