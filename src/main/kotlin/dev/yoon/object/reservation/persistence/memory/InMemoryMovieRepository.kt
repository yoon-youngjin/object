package dev.yoon.`object`.reservation.persistence.memory

import dev.yoon.`object`.reservation.domain.Movie
import dev.yoon.`object`.reservation.service.MovieRepository

class InMemoryMovieRepository : MovieRepository, InMemoryRepository<Movie>() {
    override fun getMovie(movieId: Long): Movie {
        return findOne { it.id == movieId }
    }
}