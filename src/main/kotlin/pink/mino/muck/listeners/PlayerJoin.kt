package pink.mino.muck.listeners

import org.bukkit.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import pink.mino.muck.Main
import pink.mino.muck.utils.Settings

class PlayerJoin : Listener {
    private var started: Boolean = false
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        Main.registerRecipes()
        e.player.discoverRecipes(Main.recipeKeys)
        e.player.addPotionEffect(PotionEffect(PotionEffectType.SLOW_DIGGING, 999999999, 0, false, false))
        started = Settings.instance.data!!.get("started") as Boolean
        if (!started) {
            val spawn = Location(Bukkit.getServer().getWorld("Muck"), 0.5, 36.5, 0.5)
            val effects = e.player.activePotionEffects
            for (effect in effects) {
                e.player.removePotionEffect(effect.type)
            }
            var inv = e.player.inventory

            // clear main inventory
            inv.clear()

            // clear armour slots
            inv.setArmorContents(null)
            e.player.setItemOnCursor(ItemStack(Material.AIR))

            val openInventory = e.player.openInventory;
            if (openInventory.type == InventoryType.CRAFTING) {
                openInventory.topInventory.clear()
            }
            e.player.exp = 0.0F
            e.player.level = 0
            e.player.gameMode = GameMode.ADVENTURE
            e.player.isFlying = false
            e.player.health = 10.0
            e.player.foodLevel = 10
            // Add a reset powerups thingy.
            e.player.teleport(spawn)
        }
    }
}