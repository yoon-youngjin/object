package dev.yoon.`object`.reservation.persistence.memory

import java.util.function.Predicate

abstract class InMemoryRepository<T> {
    private var currentId: Long = 1L
    private val objects = mutableListOf<T>()

    protected fun findMany(condition: Predicate<T>): List<T> {
        return objects.stream().filter(condition).toList()
    }

    protected fun findOne(condition: Predicate<T>): T {
        return objects.stream().filter(condition).findFirst().orElse(null)
    }

    fun insert(obj: T) {
        setIdIfPossible(obj)
        objects.add(obj)
    }

    private fun setIdIfPossible(obj: T) {
        try {
            val idField = obj!!::class.java.getDeclaredField("id")
            idField.setAccessible(true)
            idField.set(obj, currentId)
            currentId++
        } catch (e: Exception) {
            //
        }
    }
}