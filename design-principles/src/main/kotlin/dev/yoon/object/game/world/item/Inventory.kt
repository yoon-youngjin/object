package dev.yoon.`object`.game.world.item

class Inventory(vararg items: Item) : Carrier {
    private val items: MutableList<Item> = items.toMutableList()
    override fun items(): List<Item> {
        return items
    }

    override fun find(itemName: String): Item? {
        return items.firstOrNull { it.name == itemName }
    }

    override fun add(item: Item) {
        this.items.add(item)
    }

    override fun remove(item: Item) {
        this.items.remove(item)
    }
}