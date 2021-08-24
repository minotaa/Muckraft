package pink.mino.muck.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class BlockDamage : Listener {
    @EventHandler
    fun onBlockDamage(e: BlockDamageEvent) {
        if (e.block.type === Material.BIRCH_LOG || e.block.type === Material.STRIPPED_BIRCH_LOG) {
            if (e.itemInHand.type !== Material.WOODEN_AXE && e.itemInHand.type !== Material.STONE_AXE && e.itemInHand.type !== Material.IRON_AXE && e.itemInHand.type !== Material.DIAMOND_AXE && e.itemInHand.type !== Material.NETHERITE_AXE) {
                e.isCancelled = true
                return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Wooden Axe.")
            }
        } else if (e.block.type === Material.ACACIA_LOG || e.block.type === Material.STRIPPED_ACACIA_LOG) {
            if (e.itemInHand.type !== Material.STONE_AXE && e.itemInHand.type !== Material.IRON_AXE && e.itemInHand.type !== Material.DIAMOND_AXE && e.itemInHand.type !== Material.NETHERITE_AXE) {
                e.isCancelled = true
                return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Stone Axe.")
            }
        } else if (e.block.type === Material.SPRUCE_LOG || e.block.type === Material.STRIPPED_SPRUCE_LOG) {
            if (e.itemInHand.type !== Material.IRON_AXE && e.itemInHand.type !== Material.DIAMOND_AXE && e.itemInHand.type !== Material.NETHERITE_AXE) {
                e.isCancelled = true
                return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Iron Axe.")
            }
        } else if (e.block.type === Material.DARK_OAK_LOG || e.block.type === Material.STRIPPED_DARK_OAK_LOG) {
            if (e.itemInHand.type !== Material.DIAMOND_AXE && e.itemInHand.type !== Material.NETHERITE_AXE) {
                e.isCancelled = true
                return e.player.sendMessage("${ChatColor.RED}You can't mine this block yet, you need a Diamond Axe.")
            }
        }
    }
}