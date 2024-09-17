package dev.yoon.`object`.reservation.persistence

import dev.yoon.`object`.reservation.domain.Movie

interface MovieRepository {
    fun getMovie(movieId: Long): Movie

    fun insert(movie: Movie)
}
