package dev.yoon.`object`.console

import dev.yoon.`object`.game.InputOutput
import java.util.*

class Console : InputOutput {
    private val scanner: Scanner = Scanner(System.`in`)
    override fun input(): String {
        return scanner.nextLine().toLowerCase().trim();
    }

    override fun showLine(text: String) {
        println(text)
    }

    override fun show(text: String) {
        println(text)
    }
}