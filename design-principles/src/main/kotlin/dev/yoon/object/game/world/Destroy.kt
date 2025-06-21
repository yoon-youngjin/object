package dev.yoon.`object`.game.world

import dev.yoon.`object`.game.world.item.Item
import dev.yoon.`object`.game.world.item.Source
import kotlin.random.Random


class Destroy(
    private val first: Source,
    private val second: Source,
    private val itemName: String,
) {
    fun isPossible(): Boolean {
        return contains(first) || contains(second)
    }

    fun contains(source: Source): Boolean {
        return source.find(itemName) != null
    }

    fun perform() {
        if (!isPossible()) {
            throw IllegalStateException()
        }

        if (contains(first) && contains(second)) {
            if (Random.nextInt(2) == 0) {
                first.remove(Item(itemName))
            } else {
                second.remove(Item(itemName))
            }
            return
        }

        if (contains(first)) {
            first.remove(Item(itemName))
            return
        }

        second.remove(Item(itemName))
    }
}