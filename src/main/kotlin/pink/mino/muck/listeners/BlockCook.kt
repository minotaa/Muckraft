package pink.mino.muck.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockCookEvent

class BlockCook : Listener {
    @EventHandler
    fun onBlockCook(e: BlockCookEvent) {
        if (e.result.type === Material.NETHERITE_SCRAP) {
            e.result.type = Material.NETHERITE_INGOT
        }
    }
}