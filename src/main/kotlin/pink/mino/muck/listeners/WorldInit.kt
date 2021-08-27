package pink.mino.muck.listeners

import org.bukkit.Difficulty
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.WorldInitEvent

class WorldInit : Listener {
    @EventHandler
    fun onWorldInit(e: WorldInitEvent) {
        val world = e.world
        world.worldBorder.setCenter(0.5, 0.5)
        world.worldBorder.size = 2000.0
        world.difficulty = Difficulty.NORMAL
    }
}