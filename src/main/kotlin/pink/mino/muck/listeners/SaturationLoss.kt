package pink.mino.muck.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent
import kotlin.random.Random

class SaturationLoss : Listener {
    @EventHandler
    fun onSaturationLoss(e: FoodLevelChangeEvent) {
        val player = e.entity as Player
        if (player.hasMetadata("saturation_loss_reduction")) {
            val current: Int = player.getMetadata("saturation_loss_reduction")[0].value().toString().toInt()
            val chances = Random.nextInt(100)
            if (chances < current) {
                e.isCancelled = true
            }
        }
    }
}