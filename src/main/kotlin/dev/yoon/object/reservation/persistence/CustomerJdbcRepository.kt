package dev.yoon.`object`.reservation.persistence

import dev.yoon.`object`.reservation.domain.Customer
import dev.yoon.`object`.reservation.service.CustomerRepository
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository


@Repository
class CustomerJdbcRepository(
    private val jdbcClient: JdbcClient
) : CustomerRepository {
    override fun find(id: Long): Customer? {
        TODO()
    }
}