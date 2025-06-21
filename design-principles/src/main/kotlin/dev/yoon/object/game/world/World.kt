package dev.yoon.`object`.game.world

import dev.yoon.`object`.game.InputOutput
import dev.yoon.`object`.game.world.item.Source
import dev.yoon.`object`.game.world.item.Target
import dev.yoon.`object`.game.world.player.Player
import dev.yoon.`object`.game.world.worldmap.Direction

class World(
    private val player: Player,
    private val io: InputOutput,
) {
    fun tryMove(direction: Direction) {
        if (player.canMove(direction)) {
            player.move(direction)
            showRoom()
        }
        showBlocked()
    }

    fun throwItem(itemName: String) {
        transfer(player, player.worldMap, itemName, itemName + "을(를) 어딘가로 던졌습니다.", itemName + "을(를) 던질 수 없습니다.")
    }

    fun destroyItem(itemName: String) {
        val destroy = Destroy(player, player.currentRoom(), itemName)
        if (destroy.isPossible()) {
            destroy.perform()
            io.showLine(itemName + "을(를) 파괴했습니다.");
            return
        }

        io.showLine(itemName + "을(를) 파괴할 수 없습니다.")
    }

    fun dropItem(itemName: String) {
        transfer(
            player, player.currentRoom(), itemName,
            itemName + "을(를) 버렸습니다.",
            itemName + "을(를) 버릴 수 없습니다."
        );
    }

    fun takeItem(itemName: String) {
        transfer(
            player.currentRoom(), player, itemName,
            itemName + "을(를) 얻었습니다.",
            itemName + "을(를) 얻을 수 없습니다."
        )
    }

    fun transfer(
        source: Source,
        target: Target,
        itemName: String,
        success: String,
        fail: String,
    ) {
        val transfer = Transfer(source, target, itemName)
        if (transfer.isPossible()) {
            transfer.transfer()
            io.showLine(success)
            return
        }

        io.showLine(fail);
    }

    fun showInventory() {
        io.showLine(player.items().map { it.name }.toString())
    }

    fun showUnknownCommand() {
        io.showLine("이해할 수 없는 명령어입니다.")
    }

    fun showRoom() {
        io.showLine(("당신은 [" + player.currentRoom().name) + "]에 있습니다.")
        io.showLine(player.currentRoom().description)
    }

    private fun showBlocked() {
        io.showLine("이동할 수 없습니다.")
    }
}