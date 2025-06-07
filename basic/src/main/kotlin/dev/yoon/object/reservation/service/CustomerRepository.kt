package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.Customer


interface CustomerRepository {
    fun find(id: Long): Customer?
}
