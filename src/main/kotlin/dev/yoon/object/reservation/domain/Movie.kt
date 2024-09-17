package dev.yoon.`object`.reservation.domain

import dev.yoon.`object`.generic.Money

data class Movie(
    val id: Long,
    val title: String,
    val runningTime: Int,
    val fee: Money,
)
