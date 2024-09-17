package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

data class Reservation(
    val id: Long,
    val customerId: Long,
    val screeningId: Long,
    val audienceCount: Int,
    val fee: Money,
)
