package pink.mino.muck.listeners

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import org.bukkit.*
import org.bukkit.Bukkit.getServer
import org.bukkit.command.CommandSender
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
import org.jetbrains.annotations.NotNull
import pink.mino.muck.utils.ApplyPowerup
import pink.mino.muck.utils.GuiBuilder
import pink.mino.muck.utils.Settings
import pink.mino.muck.utils.powerups.CommonPowerups
import pink.mino.muck.utils.powerups.LegendaryPowerups
import pink.mino.muck.utils.powerups.RarePowerups
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
                    var rarity: Any? = null
                    if (block.getMetadata("rarity")[0] !== null) {
                        when {
                            block.getMetadata("rarity")[0].value() === "free" -> {
                                val odds = Random.nextInt(101)
                                when {
                                    odds > 90 -> {
                                        rarity = "common"
                                    }
                                    odds > 99 -> {
                                        rarity = "rare"
                                    }
                                    odds > 100 -> {
                                        rarity = "legendary"
                                    }
                                }
                            }
                            block.getMetadata("rarity")[0].value() === "common" -> {
                                val odds = Random.nextInt(101)
                                when {
                                    odds > 80 -> {
                                        rarity = "common"
                                    }
                                    odds > 18 -> {
                                        rarity = "rare"
                                    }
                                    odds > 2 -> {
                                        rarity = "legendary"
                                    }
                                }
                            }
                            block.getMetadata("rarity")[0].value() === "diamond" -> {
                                val odds = Random.nextInt(101)
                                when {
                                    odds > 85 -> {
                                        rarity = "rare"
                                    }
                                    odds > 15 -> {
                                        rarity = "legendary"
                                    }
                                }
                            }
                            block.getMetadata("rarity")[0].value() === "gold" -> {
                                rarity = "legendary"
                            }
                        }
                    }
                    var reward: Any? = null
                    when {
                        rarity === "legendary" -> {
                            val choice = Random.nextInt(LegendaryPowerups.values().size)
                            reward = LegendaryPowerups.values()[choice]
                        }
                        rarity === "rare" -> {
                            val choice = Random.nextInt(RarePowerups.values().size)
                            reward = RarePowerups.values()[choice]
                        }
                        rarity === "common" -> {
                            val choice = Random.nextInt(CommonPowerups.values().size)
                            reward = CommonPowerups.values()[choice]
                        }
                    }
                    if (reward != null) {
                        ApplyPowerup.applyPower(player, reward)
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
            } else if (block.type === Material.RESPAWN_ANCHOR) {
                val gui = GuiBuilder().rows(2).name("Revive your Friends")
                val list = Settings.instance.data!!.get("list") as ArrayList<*>
                for ((i, p) in list.withIndex()) {
                    if (p !== player.name) {
                        val playerItem = ItemStack(Material.SKELETON_SKULL)
                        val meta = playerItem.itemMeta
                        meta.setDisplayName("$p")
                        val world = Bukkit.getWorld("Muck")
                        var day = 0L
                        if (world !== null) {
                            day = world.fullTime / 24000
                        }
                        meta.lore = listOf("${ChatColor.GRAY}Click to revive ${ChatColor.YELLOW}${p}${ChatColor.GRAY}.", "${ChatColor.GRAY}Price: ${ChatColor.GOLD}${50 * (day + 1)}")
                        playerItem.itemMeta = meta
                        gui.item(i, playerItem).onClick runnable@{
                            it.isCancelled = true
                            val item = it.cursor
                            if (item != null) {
                                Bukkit.dispatchCommand(player as CommandSender, "revive ${meta.displayName}")
                            }
                            it.clickedInventory!!.close()
                        }
                    }
                }
                player.openInventory(gui.make())
            }
        }
    }
}