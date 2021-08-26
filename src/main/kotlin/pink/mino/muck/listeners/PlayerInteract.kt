package pink.mino.muck.listeners

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import org.bukkit.*
import org.bukkit.Bukkit.getServer
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.random.Random


class PlayerInteract : Listener {

    private val monsters: List<EntityType> = listOf(EntityType.STRAY, EntityType.HUSK, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIE, EntityType.SKELETON)

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        val block = e.clickedBlock
        val player = e.player
        if (block != null) {
            if (block.type === Material.CHEST) {
                if (block.hasMetadata("loot")) {
                    e.isCancelled = true
                    var amount = 0
                    for (item in player.inventory.contents) {
                        if (item != null) {
                            if (item.type === Material.GOLD_INGOT) amount += item.amount
                        }
                    }
                    if (amount < block.getMetadata("cost")[0].value().toString().toInt()) {
                        return player.sendMessage("${ChatColor.RED}You can't afford this, you need ${ChatColor.WHITE}${block.getMetadata("cost")[0].value().toString().toInt() - amount} ${ChatColor.GOLD}⛃${ChatColor.RED} more gold.")
                    }
                    player.inventory.removeItem(ItemStack(Material.GOLD_INGOT, block.getMetadata("cost")[0].value().toString().toInt()))
                    val location = block.location
                    val world = e.player.world
                    world.playEffect(location, Effect.SMOKE, 0)
                    world.playSound(location, Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 0.5F)
                    if (block.getMetadata("rarity")[0] !== null) {
                        when {
                            block.getMetadata("rarity")[0].value() === "free" -> {
                                world.dropItemNaturally(location, ItemStack(Material.STICK))
                            }
                            block.getMetadata("rarity")[0].value() === "common" -> {
                                world.dropItemNaturally(location, ItemStack(Material.DIAMOND))
                            }
                            block.getMetadata("rarity")[0].value() === "diamond" -> {
                                world.dropItemNaturally(location, ItemStack(Material.GOLDEN_AXE))
                            }
                            block.getMetadata("rarity")[0].value() === "gold" -> {
                                world.dropItemNaturally(location, ItemStack(Material.DIAMOND_AXE))
                            }
                            block.getMetadata("rarity")[0].value() === "emerald" -> {
                                world.dropItemNaturally(location, ItemStack(Material.ELYTRA))
                            }
                        }
                    }
                    location.block.type = Material.AIR
                    for (en in block.location.getNearbyEntities(5.0, 5.0, 5.0)) {
                        if (HologramsAPI.isHologramEntity(en)) en.remove()
                    }
                }
            } else if (block.type === Material.TARGET) {
                block.type = Material.AIR
                val world = e.player.world
                for (en in block.location.getNearbyEntities(5.0, 5.0, 5.0)) {
                    if (HologramsAPI.isHologramEntity(en)) en.remove()
                }
                getServer().broadcastMessage("${ChatColor.GREEN}${e.player.name} started a battle!")
                for (i in 0..2) {
                    val x = block.location.x
                    val z = block.location.z
                    val randomLocation = Location(block.location.world, x + Random.nextDouble(1.0, 2.0), block.location.y, z + Random.nextDouble(1.0, 2.0))
                    val entity = world.spawnEntity(randomLocation, monsters.random())
                    entity.isGlowing = true
                    val livingEntity = entity as LivingEntity
                    livingEntity.customName = "${ChatColor.RED}☠"
                    livingEntity.isCustomNameVisible = true
                    livingEntity.equipment?.helmet = ItemStack(Material.LEATHER_HELMET)
                    livingEntity.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000000, 0))
                }
            }
        }
    }
}