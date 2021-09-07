package pink.mino.muck.listeners

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerDamage : Listener {
    @EventHandler
    fun onPlayerDamage(e: EntityDamageEvent) {
        if (e.entity.type === EntityType.PLAYER) {
            if (e.cause === EntityDamageEvent.DamageCause.FALL) {
                e.isCancelled = true
            }
            val player = e.entity as Player
            if (player.health < 4.0) {
                if (player.hasMetadata("adrenaline")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 45, player.getMetadata("adrenaline")[0].value().toString().toInt(), false, false))
                }
                if (player.hasMetadata("berserk")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, player.getMetadata("berserk")[0].value().toString().toInt(), 1, false, false))
                }
            }
        }
    }
}