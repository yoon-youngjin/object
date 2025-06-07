package dev.yoon.`object`.reservation.domain

class SequenceCondition(
    private val sequence: Int
) : DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean {
        return screening.isSequence(sequence)
    }
}