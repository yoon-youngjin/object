package dev.yoon.`object`

data class WorldMap(
    val size: Size,
    val rooms: ArrayList<Room>
) {
    fun isBlocked(position: Position): Boolean {
        return isExcluded(position) || roomAt(position) == null
    }

    fun roomAt(position: Position): Room? {
        return rooms.getOrNull(size.indexOf(position))
    }

    private fun isExcluded(position: Position) = size.contains(position).not()

    companion object {
        fun of(size: Size, vararg rooms: Room): WorldMap {
            val result = ArrayList<Room>(size.area())
            for (room in rooms) {
                val index = size.indexOf(room.position)
                result[index] = room
            }

            return WorldMap(
                size = size,
                rooms = result,
            )
        }
    }
}

