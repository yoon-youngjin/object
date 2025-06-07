package dev.yoon.`object`

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

    companion object {
        fun with(width: Int, height: Int): Size {
            return Size(
                width = width,
                height = height,
            )
        }
    }
}
