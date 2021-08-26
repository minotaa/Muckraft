package pink.mino.muck.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class EntityDeath : Listener {
    @EventHandler
    fun onEntityDeath(e: EntityDeathEvent) {
        val entity = e.entity
        if (entity.type === EntityType.COW) {
            e.drops.remove(ItemStack(Material.LEATHER))
        }
        if (entity.type === EntityType.VILLAGER || entity.type === EntityType.ZOMBIE_VILLAGER || entity.type === EntityType.ZOMBIE || entity.type === EntityType.SKELETON || entity.type === EntityType.STRAY || entity.type === EntityType.HUSK) {
            e.drops.clear()
            if (entity.customName === "${ChatColor.RED}☠") {
                e.drops.add(ItemStack(Material.GOLD_INGOT, Random.nextInt(10, 40)))
            } else {
                e.drops.add(ItemStack(Material.GOLD_INGOT, Random.nextInt(5, 20)))
            }

        }
    }
}