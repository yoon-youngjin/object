package dev.yoon.`object`

data class Position(
    val x: Int,
    val y: Int,
) {
    fun shift(direction: Direction): Position {
        return when (direction) {
            Direction.NORTH -> of(x, y - 1)
            Direction.SOUTH -> of(x + 1, y)
            Direction.EAST -> of(x, y + 1)
            Direction.WEST -> of(x - 1, y)
        }
    }

    companion object {
        fun of(x: Int, y: Int): Position {
            return Position(
                x = x,
                y = y,
            )
        }
    }
}
