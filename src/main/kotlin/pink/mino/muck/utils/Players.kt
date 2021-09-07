package pink.mino.muck.utils

import org.bukkit.entity.Player

class Players {
    companion object {
        fun broadcast(players: Collection<Player>, message: String) {
            for (player in players) {
                player.sendMessage(message)
            }
        }
    }
}