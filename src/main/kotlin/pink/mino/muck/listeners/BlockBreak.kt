package pink.mino.muck.listeners

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class BlockBreak : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onBlockBreak(e: BlockBreakEvent) {
        if (e.player.world.name === "Muck") {
            if (
                e.block.type === Material.DIAMOND_ORE ||
                e.block.type === Material.IRON_ORE ||
                e.block.type === Material.ANCIENT_DEBRIS ||
                e.block.type === Material.STONE ||
                e.block.type === Material.OAK_LOG ||
                e.block.type === Material.STRIPPED_OAK_LOG ||
                e.block.type === Material.BIRCH_LOG ||
                e.block.type === Material.STRIPPED_BIRCH_LOG ||
                e.block.type === Material.DARK_OAK_LOG ||
                e.block.type === Material.STRIPPED_DARK_OAK_LOG ||
                e.block.type === Material.SPRUCE_LOG ||
                e.block.type === Material.STRIPPED_SPRUCE_LOG ||
                e.block.type === Material.SPRUCE_LOG ||
                e.block.type === Material.STRIPPED_SPRUCE_LOG ||
                e.block.type === Material.ACACIA_LOG ||
                e.block.type === Material.STRIPPED_ACACIA_LOG ||
                e.block.type === Material.COAL_ORE ||
                e.block.type === Material.GOLD_ORE ||
                e.block.type === Material.OAK_LEAVES ||
                e.block.type === Material.SPRUCE_LEAVES ||
                e.block.type === Material.ACACIA_LEAVES ||
                e.block.type === Material.DARK_OAK_LEAVES ||
                e.block.type === Material.BIRCH_LEAVES
            ) {
                if (e.isDropItems) {
                    val location = e.block.location
                    val world = e.block.world
                    val player = e.player
                    e.isDropItems = false
                    if (e.block.type === Material.OAK_LOG || e.block.type === Material.STRIPPED_OAK_LOG) {
                        world.dropItemNaturally(location, ItemStack(Material.OAK_LOG, Random.nextInt(1, 3)))
                    } else if (e.block.type === Material.BIRCH_LOG || e.block.type === Material.STRIPPED_BIRCH_LOG) {
                        if (e.player.inventory.itemInMainHand.type !== Material.WOODEN_AXE && e.player.inventory.itemInMainHand.type !== Material.STONE_AXE && e.player.inventory.itemInMainHand.type !== Material.IRON_AXE && e.player.inventory.itemInMainHand.type !== Material.DIAMOND_AXE && e.player.inventory.itemInMainHand.type !== Material.NETHERITE_AXE) {
                            e.isCancelled = true
                            return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Wooden Axe.")
                        } else {
                            world.dropItemNaturally(location, ItemStack(Material.BIRCH_LOG, Random.nextInt(1, 3)))
                        }
                    } else if (e.block.type === Material.ACACIA_LOG || e.block.type === Material.STRIPPED_ACACIA_LOG) {
                        if (e.player.inventory.itemInMainHand.type !== Material.STONE_AXE && e.player.inventory.itemInMainHand.type !== Material.IRON_AXE && e.player.inventory.itemInMainHand.type !== Material.DIAMOND_AXE && e.player.inventory.itemInMainHand.type !== Material.NETHERITE_AXE) {
                            e.isCancelled = true
                            return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Stone Axe.")
                        } else {
                            world.dropItemNaturally(location, ItemStack(Material.ACACIA_LOG, Random.nextInt(1, 3)))
                        }
                    } else if (e.block.type === Material.SPRUCE_LOG || e.block.type === Material.STRIPPED_SPRUCE_LOG) {
                        if (e.player.inventory.itemInMainHand.type !== Material.IRON_AXE && e.player.inventory.itemInMainHand.type !== Material.DIAMOND_AXE && e.player.inventory.itemInMainHand.type !== Material.NETHERITE_AXE) {
                            e.isCancelled = true
                            return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Iron Axe.")
                        } else {
                            world.dropItemNaturally(location, ItemStack(Material.SPRUCE_LOG, Random.nextInt(1, 3)))
                        }
                    } else if (e.block.type === Material.DARK_OAK_LOG || e.block.type === Material.STRIPPED_DARK_OAK_LOG) {
                        if (e.player.inventory.itemInMainHand.type !== Material.DIAMOND_AXE && e.player.inventory.itemInMainHand.type !== Material.NETHERITE_AXE) {
                            e.isCancelled = true
                            return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Diamond Axe.")
                        } else {
                            world.dropItemNaturally(location, ItemStack(Material.DARK_OAK_LOG, Random.nextInt(1, 3)))
                        }
                    }
                    when {
                        e.block.type === Material.DIAMOND_ORE -> {
                            if (e.player.inventory.itemInMainHand.type === Material.IRON_PICKAXE || e.player.inventory.itemInMainHand.type === Material.DIAMOND_PICKAXE || e.player.inventory.itemInMainHand.type === Material.NETHERITE_PICKAXE) {
                                world.dropItemNaturally(location, ItemStack(Material.DIAMOND_ORE, Random.nextInt(3, 5)))
                            } else {
                                e.isCancelled = true
                                return player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need an Iron Pickaxe")
                            }
                        }
                        e.block.type === Material.ANCIENT_DEBRIS -> {
                            if (e.player.inventory.itemInMainHand.type === Material.DIAMOND_PICKAXE || e.player.inventory.itemInMainHand.type === Material.NETHERITE_PICKAXE) {
                                world.dropItemNaturally(
                                    location,
                                    ItemStack(Material.ANCIENT_DEBRIS, Random.nextInt(3, 5))
                                )
                            } else {
                                e.isCancelled = true
                                return player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Diamond Pickaxe")
                            }
                        }
                        e.block.type === Material.STONE -> {
                            if (e.player.inventory.itemInMainHand.type === Material.WOODEN_PICKAXE || e.player.inventory.itemInMainHand.type === Material.STONE_PICKAXE || e.player.inventory.itemInMainHand.type === Material.IRON_PICKAXE || e.player.inventory.itemInMainHand.type === Material.DIAMOND_PICKAXE || e.player.inventory.itemInMainHand.type === Material.NETHERITE_PICKAXE) {
                                world.dropItemNaturally(
                                    location,
                                    ItemStack(Material.COBBLESTONE, Random.nextInt(1, 10))
                                )
                            } else {
                                e.isCancelled = true
                                return player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Wooden Pickaxe")
                            }
                        }
                        e.block.type === Material.COAL_ORE -> {
                            if (e.player.inventory.itemInMainHand.type === Material.WOODEN_PICKAXE || e.player.inventory.itemInMainHand.type === Material.STONE_PICKAXE || e.player.inventory.itemInMainHand.type === Material.IRON_PICKAXE || e.player.inventory.itemInMainHand.type === Material.DIAMOND_PICKAXE || e.player.inventory.itemInMainHand.type === Material.NETHERITE_PICKAXE) {
                                world.dropItemNaturally(location, ItemStack(Material.COAL, Random.nextInt(1, 10)))
                            } else {
                                e.isCancelled = true
                                return player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Wooden Pickaxe")
                            }
                        }
                        e.block.type === Material.IRON_ORE -> {
                            if (e.player.inventory.itemInMainHand.type === Material.STONE_PICKAXE || e.player.inventory.itemInMainHand.type === Material.IRON_PICKAXE || e.player.inventory.itemInMainHand.type === Material.DIAMOND_PICKAXE || e.player.inventory.itemInMainHand.type === Material.NETHERITE_PICKAXE) {
                                world.dropItemNaturally(location, ItemStack(Material.IRON_ORE, Random.nextInt(3, 5)))
                            } else {
                                e.isCancelled = true
                                return player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Stone Pickaxe")
                            }
                        }
                        e.block.type === Material.GOLD_ORE -> {
                            if (e.player.inventory.itemInMainHand.type === Material.STONE_PICKAXE || e.player.inventory.itemInMainHand.type === Material.IRON_PICKAXE || e.player.inventory.itemInMainHand.type === Material.DIAMOND_PICKAXE || e.player.inventory.itemInMainHand.type === Material.NETHERITE_PICKAXE) {
                                world.dropItemNaturally(location, ItemStack(Material.GOLD_ORE, Random.nextInt(1, 10)))
                            } else {
                                e.isCancelled = true
                                return player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Stone Pickaxe")
                            }
                        }
                    }
                    e.block.type = Material.AIR
                }
            } else {
                if (!e.block.hasMetadata("placed")) {
                    e.isCancelled = true
                }
            }
        }
    }
}