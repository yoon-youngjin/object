package dev.yoon.`object`.game.world.item

interface Source {
    fun find(itemName: String): Item?
    fun remove(item: Item)
}