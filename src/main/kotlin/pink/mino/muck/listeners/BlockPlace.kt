package pink.mino.muck.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import pink.mino.muck.Main

class BlockPlace : Listener {
    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val block = e.blockPlaced
        if (
            block.type === Material.DIAMOND_ORE ||
            block.type === Material.IRON_ORE ||
            block.type === Material.ANCIENT_DEBRIS ||
            block.type === Material.STONE ||
            block.type === Material.OAK_LOG ||
            block.type === Material.STRIPPED_OAK_LOG ||
            block.type === Material.COAL_ORE ||
            block.type === Material.GOLD_ORE ||
            block.type === Material.OAK_LEAVES
        ) {
            e.isCancelled = true
            e.player.sendMessage("${ChatColor.RED}You can't place this block!")
        } else {
            block.setMetadata("placed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "yes"))
        }
    }
}