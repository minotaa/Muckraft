package pink.mino.muck.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class PlayerDamage : Listener {
    @EventHandler
    fun onPlayerDamage(e: EntityDamageEvent) {
        if (e.cause === EntityDamageEvent.DamageCause.FALL) {
            e.isCancelled = true
        }
    }
}