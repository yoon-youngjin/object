package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

data class Reservation(
    val customer: Customer,
    val screening: Screening,
    val audienceCount: Int,
    val fee: Money,
)
