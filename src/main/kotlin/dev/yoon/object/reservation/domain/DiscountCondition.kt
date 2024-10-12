package dev.yoon.`object`.reservation.domain

interface DiscountCondition {
    fun isSatisfiedBy(screening: Screening): Boolean
}
