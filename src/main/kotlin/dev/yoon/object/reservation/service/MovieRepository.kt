package dev.yoon.`object`.reservation.service

import dev.yoon.`object`.reservation.domain.Movie

interface MovieRepository {
    fun find(id: Long): Movie?

    fun insert(movie: Movie)
}
