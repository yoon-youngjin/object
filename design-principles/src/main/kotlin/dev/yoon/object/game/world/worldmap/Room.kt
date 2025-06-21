package dev.yoon.`object`.game.world.worldmap

import dev.yoon.`object`.game.world.item.ForwardingCarrier
import dev.yoon.`object`.game.world.item.Inventory

class Room(
    val position: Position,
    val name: String,
    val description: String,
    inventory: Inventory? = null,
): ForwardingCarrier(inventory!!)
