package dev.yoon.`object`.game.world.player

import dev.yoon.`object`.game.world.worldmap.Direction
import dev.yoon.`object`.game.world.worldmap.Position
import dev.yoon.`object`.game.world.worldmap.Room
import dev.yoon.`object`.game.world.worldmap.WorldMap
import dev.yoon.`object`.game.world.item.ForwardingCarrier
import dev.yoon.`object`.game.world.item.Inventory

data class Player(
    val worldMap: WorldMap,
    var position: Position,
    val inventory: Inventory,
) : ForwardingCarrier(inventory) {
    fun canMove(direction: Direction): Boolean {
        return worldMap.isBlocked(this.position.shift(direction))
    }

    fun move(direction: Direction) {
        if (canMove(direction).not()) {
            throw IllegalArgumentException()
        }

        this.position = position.shift(direction)
    }

    fun currentRoom(): Room {
        return worldMap.roomAt(this.position) ?: throw IllegalArgumentException()
    }
}
