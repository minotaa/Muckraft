package pink.mino.muck.listeners

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import kotlin.random.Random

class EntityDamage : Listener {
    @EventHandler
    fun onEntityDamage(e: EntityDamageByEntityEvent) {
        val killer = e.damager
        if (killer.type === EntityType.PLAYER) {
            if (killer.hasMetadata("strength")) {
                e.damage = e.damage + (0.1 * killer.getMetadata("strength")[0].value().toString().toInt())
            }
            if (killer.hasMetadata("lifesteal")) {
                val player = killer as Player
                player.health += (0.1 * killer.getMetadata("lifesteal")[0].value().toString().toInt())
            }
            if (killer.hasMetadata("more_critical")) {
                val current: Int = killer.getMetadata("more_critical")[0].value().toString().toInt()
                val chances = Random.nextInt(100)
                if (chances < current) {
                    e.damage *= 2
                }
            }
            if (killer.hasMetadata("super_crits")) {
                val current: Int = killer.getMetadata("super_crits")[0].value().toString().toInt()
                val chances = Random.nextInt(100)
                if (chances < (current * 0.1)) {
                    e.damage *= 5
                }
            }
            if (killer.hasMetadata("speed_boost")) {
                e.damage += (killer.velocity.length() + killer.getMetadata("speed_boost")[0].value().toString().toInt())
            }
            if (killer.hasMetadata("smiting")) {
                val current: Int = killer.getMetadata("smiting")[0].value().toString().toInt()
                val chances = Random.nextInt(100)
                if (chances < (current * 0.1)) {
                    val world = e.entity.world
                    world.strikeLightning(e.entity.location)
                }
            }
        }
        if (e.entity.type === EntityType.PLAYER) {
            if (e.entity.hasMetadata("defense")) {
                e.damage = e.damage - (0.1 * e.entity.getMetadata("defense")[0].value().toString().toInt())
            }
        }
    }
}