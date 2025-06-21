package dev.yoon.`object`.game.world.item

abstract class ForwardingCarrier(
    private val carrier: Carrier,
): Carrier {
    override fun items(): List<Item> {
        return carrier.items()
    }

    override fun find(itemName: String): Item? {
        return carrier.find(itemName)
    }

    override fun add(item: Item) {
        return carrier.add(item)
    }

    override fun remove(item: Item) {
        return carrier.remove(item)
    }


}