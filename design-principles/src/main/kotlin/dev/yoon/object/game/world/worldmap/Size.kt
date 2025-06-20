package dev.yoon.`object`.game.world.worldmap

import kotlin.random.Random

data class Size(
    val width: Int,
    val height: Int,
) {
    fun contains(position: Position): Boolean {
        return (position.x in 0..<width) && (position.y in 0..<height)
    }

    fun area(): Int {
        return width * height
    }

    fun indexOf(position: Position): Int {
        return position.x + position.y * width
    }

    fun anyPosition(): Position {
        val random = Random
        return Position.of(random.nextInt(width), random.nextInt(height))
    }

    companion object {
        fun with(width: Int, height: Int): Size {
            return Size(
                width = width,
                height = height,
            )
        }
    }
}
