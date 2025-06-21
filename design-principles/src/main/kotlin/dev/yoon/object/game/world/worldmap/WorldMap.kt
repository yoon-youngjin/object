package dev.yoon.`object`.game.world.worldmap

import dev.yoon.`object`.game.world.item.Item
import dev.yoon.`object`.game.world.item.Target

data class WorldMap(
    val size: Size,
    val rooms: ArrayList<Room>
): Target {
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

    override fun add(item: Item) {
        val position: Position = size.anyPosition()
        if (isBlocked(position)) {
            return
        }

        roomAt(position)!!.add(item)
    }
}

