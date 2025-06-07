package dev.yoon.`object`

import java.util.*

class Game(
    private val player: Player,
    private var running: Boolean,
) {
    fun run() {
        welcome()
        play()
        farewell()
    }

    private fun farewell() {
        println("\n게임을 종료합니다.")
    }

    private fun play() {
        val scanner = Scanner(System.`in`)
        start()

        while (isRunning()) {
            val input = inputCommand(scanner)
            val command = CommandParser.parseCommand(input)
            executeCommand(command)
        }
    }

    private fun executeCommand(command: Command) {
        when (command) {
            is Command.Move -> tryMove(command.direction)
            is Command.Help -> showHelp()
            is Command.Look -> showRoom()
            is Command.Quit -> stop()
            is Command.Unknown -> showUnknownCommand()
        }
    }

    private fun inputCommand(scanner: Scanner): String {
        showPrompt()
        return input(scanner)
    }

    private fun input(scanner: Scanner): String {
        return scanner.nextLine()
    }

    private fun showPrompt() {
        print("> ")
    }

    private fun stop() {
        running = false
    }

    private fun showUnknownCommand() {
        println("이해할 수 없는 명령어입니다.")
    }

    private fun tryMove(direction: Direction) {
        if (player.worldMap.isBlocked(player.position.shift(direction))) {
            showBlocked()
        } else {
            player.move(player.position.shift(direction))
            showRoom()
        }
    }


    private fun showBlocked() {
        println("이동할 수 없습니다.")
    }

    private fun isRunning(): Boolean {
        return running
    }

    private fun start() {
        running = true
    }

    private fun welcome() {
        showGreeting()
        showRoom()
        showHelp()
    }

    private fun showHelp() {
        println("다음 명령어를 사용할 수 있습니다.")
        println("go {north|east|south|west} - 이동, quit - 게임 종료")
    }

    private fun showRoom() {
        println(("당신은 [" + player.worldMap.roomAt(player.position)!!.name) + "]에 있습니다.")
        println(player.worldMap.roomAt(player.position)!!.description)
    }

    private fun showGreeting() {
        println("환영합니다!")
    }
}
