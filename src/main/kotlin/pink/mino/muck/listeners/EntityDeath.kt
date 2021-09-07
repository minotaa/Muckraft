package pink.mino.muck.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class EntityDeath : Listener {
    @EventHandler
    fun onEntityDeath(e: EntityDeathEvent) {
        val entity = e.entity
        val killer: Player? = e.entity.killer
        if (entity.type === EntityType.COW) {
            e.drops.remove(ItemStack(Material.LEATHER))
        }
        if (entity.type === EntityType.VILLAGER || entity.type === EntityType.ZOMBIE_VILLAGER || entity.type === EntityType.ZOMBIE || entity.type === EntityType.SKELETON || entity.type === EntityType.STRAY || entity.type === EntityType.HUSK) {
            e.drops.clear()
            var amount: Int = Random.nextInt(10, 20)
            if (killer!!.hasMetadata("increased_loot")) {
                amount *= killer.getMetadata("increased_loot")[0].value().toString().toInt()
            }
            if (entity.customName === "${ChatColor.RED}☠") {
                amount *= 2
            }
            e.drops.add(ItemStack(Material.GOLD_INGOT, amount))
        }
        if (killer!!.hasMetadata("dracula")) {
            val healthGained = 0.1 * (killer.getMetadata("dracula")[0].value().toString().toInt())
            killer.maxHealth = killer.maxHealth + healthGained
        }
    }
}