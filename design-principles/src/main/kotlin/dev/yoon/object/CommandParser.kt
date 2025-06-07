package dev.yoon.`object`

object CommandParser {
    fun parseCommand(input: String): Command {
        return parseCommand(split(input))
    }

    private fun parseCommand(commands: Array<String>): Command {
        return when (commands[0]) {
            "go" -> when (commands[1]) {
                "north" -> Command.Move(Direction.NORTH)
                "south" -> Command.Move(Direction.SOUTH)
                "east" -> Command.Move(Direction.EAST)
                "west" -> Command.Move(Direction.WEST)
                else -> Command.Unknown
            }

            "look" -> Command.Look
            "help" -> Command.Help
            "quit" -> Command.Quit
            else -> Command.Unknown
        }
    }

    private fun split(input: String): Array<String> {
        return input.toLowerCase().trim().split("\\s+").toTypedArray()
    }
}