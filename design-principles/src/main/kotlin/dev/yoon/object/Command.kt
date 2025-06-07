package dev.yoon.`object`

sealed interface Command {
    class Move(val direction: Direction): Command
    data object Unknown : Command
    data object Look : Command
    data object Help : Command
    data object Quit : Command
}
