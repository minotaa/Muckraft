package pink.mino.muck.listeners

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import pink.mino.muck.utils.Settings

class PlayerDeath : Listener {
    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        val player = e.entity
        val started = Settings.instance.data!!.get("started") as Boolean
        e.deathMessage = "${ChatColor.RED}☠${ChatColor.WHITE} ${e.deathMessage}"
        if (Settings.instance.data!!.get("list") !== null) {
            val list = Settings.instance.data!!.get("list") as ArrayList<*>
            if (started) {
                if (player.name in list) {
                    list.remove(player.name)
                    Settings.instance.data!!.set("list", list)
                    if (list.size == 0) {
                        Settings.instance.data!!.set("started", false)
                    }
                    Settings.instance.data!!.set("death.${player.name}.x", player.location.x.toString())
                    Settings.instance.data!!.set("death.${player.name}.y", player.location.y.toString())
                    Settings.instance.data!!.set("death.${player.name}.z", player.location.z.toString())
                    Settings.instance.saveData()
                }
            }
        }
    }
}