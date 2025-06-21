package dev.yoon.`object`.game

import dev.yoon.`object`.game.command.Command
import dev.yoon.`object`.game.command.CommandParser
import dev.yoon.`object`.game.world.World

class Game(
    private val world: World,
    private val io: InputOutput,
) {
    private var running: Boolean = false

    fun run() {
        welcome()
        play()
        farewell()
    }

    private fun farewell() {
        io.showLine("\n게임을 종료합니다.")
    }

    private fun play() {
        start()

        while (isRunning()) {
            val input = inputCommand()
            val command = CommandParser.parseCommand(input)
            executeCommand(command)
        }
    }

    private fun executeCommand(command: Command) {
        when (command) {
            is Command.Move -> world.tryMove(command.direction)
            is Command.Help -> showHelp()
            is Command.Look -> world.showRoom()
            is Command.Quit -> stop()
            is Command.Unknown -> world.showUnknownCommand()
            Command.Inventory -> world.showInventory()
            is Command.Take -> world.takeItem(command.itemName)
            is Command.Drop -> world.dropItem(command.itemName)
            is Command.Destroy -> world.destroyItem(command.itemName)
            is Command.Throw -> world.throwItem(command.itemName)
        }
    }

    private fun inputCommand(): String {
        showPrompt()
        return io.input()
    }

    private fun isRunning(): Boolean {
        return running
    }

    private fun start() {
        running = true
    }

    private fun welcome() {
        showGreetings()
        world.showRoom()
        showHelp()
    }

    private fun showGreetings() {
        io.showLine("환영합니다!")
    }

    private fun showPrompt() {
        io.show("> ")
    }

    private fun showHelp() {
        io.showLine("다음 명령어를 사용할 수 있습니다.")
        io.showLine("go {north|east|south|west} - 이동, quit - 게임 종료")
    }

    private fun stop() {
        running = false
    }
}
