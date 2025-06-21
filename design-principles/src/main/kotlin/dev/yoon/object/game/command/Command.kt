package dev.yoon.`object`.game.command

import dev.yoon.`object`.game.world.worldmap.Direction

sealed interface Command {
    class Move(val direction: Direction) : Command
    data object Unknown : Command
    data object Look : Command
    data object Help : Command
    data object Quit : Command
    data object Inventory : Command
    class Take(val itemName: String) : Command
    class Drop(val itemName: String) : Command
    class Destroy(val itemName: String) : Command
    class Throw(val itemName: String) : Command
}
