package dev.yoon.`object`.game.world

import dev.yoon.`object`.game.world.item.Source
import dev.yoon.`object`.game.world.item.Target

class Transfer(
    private val source: Source,
    private val target: Target,
    private val itemName: String,
) {
    fun isPossible(): Boolean {
        return source.find(itemName) != null
    }

    fun transfer() {
        if (isPossible().not()) {
            throw IllegalArgumentException()
        }

        source.find(itemName)?.run {
            source.remove(this)
            target.add(this)
        }
    }
}