package pink.mino.muck.listeners

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import pink.mino.muck.utils.Settings

class PlayerRespawn : Listener {
    @EventHandler
    fun onPlayerRespawn(e: PlayerRespawnEvent) {
        val started = Settings.instance.data!!.get("started") as Boolean
        if (started) {
            val world = Bukkit.getServer().getWorld("Muck")
            e.respawnLocation = world!!.spawnLocation
            e.player.gameMode = GameMode.SPECTATOR
            e.player.sendMessage("${ChatColor.RED}You're dead! You have been sent into Spectator mode!")
        }
    }
}