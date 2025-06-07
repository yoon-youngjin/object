package dev.yoon.`object`

data class Player(
    val worldMap: WorldMap,
    var position: Position,
) {
    fun move(position: Position) {
        this.position = position
    }
}
