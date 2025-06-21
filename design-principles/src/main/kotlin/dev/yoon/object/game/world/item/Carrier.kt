package dev.yoon.`object`.game.world.item


interface Carrier : Source, Target {
    fun items(): List<Item>
}