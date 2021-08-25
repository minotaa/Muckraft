package pink.mino.muck.listeners

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class PlayerDamage : Listener {
    @EventHandler
    fun onPlayerDamage(e: EntityDamageEvent) {
        if (e.entity.type === EntityType.PLAYER) {
            if (e.cause === EntityDamageEvent.DamageCause.FALL) {
                e.isCancelled = true
            }
        }
    }
}