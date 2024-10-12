package dev.yoon.`object`.reservation.persistence

import dev.yoon.`object`.reservation.domain.Screening
import dev.yoon.`object`.reservation.service.ScreeningRepository
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository

@Repository
class ScreeningJdbcRepository(
    private val jdbcClient: JdbcClient
) : ScreeningRepository {
    override fun find(id: Long): Screening? {
        TODO("Not yet implemented")
    }
    override fun insert(screening: Screening) {
        TODO("Not yet implemented")
    }
}
