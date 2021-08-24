package pink.mino.muck.listeners

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkLoadEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import pink.mino.muck.Main
import kotlin.random.Random

class ChunkLoad : Listener {

    private val trees: List<TreeType> = listOf(TreeType.DARK_OAK, TreeType.ACACIA, TreeType.BIRCH, TreeType.REDWOOD, TreeType.TREE)

    private fun spawnChest(chunk: Chunk, world: World) {
        val x = ((chunk.x * 16)..((chunk.x * 16) + 16)).random()
        val z = ((chunk.z * 16)..((chunk.z * 16) + 16)).random()
        val loc: Location?
        val highestBlock = world.getHighestBlockAt(x, z).y + 1
        loc = Location(world, x.toDouble(), highestBlock.toDouble(), z.toDouble())
        loc.block.type = Material.CHEST
        val list: Array<String> = arrayOf("free", "common", "diamond", "gold", "emerald")
        loc.block.setMetadata("rarity", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), list.random()))
        loc.block.setMetadata("loot", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "yes"))
        val loc2 = Location(world, x.toDouble() + 0.5, highestBlock.toDouble() + 1.5, z.toDouble() + 0.5)
        val hologram = HologramsAPI.createHologram(JavaPlugin.getPlugin(Main::class.java), loc2)
        when {
            loc.block.getMetadata("rarity")[0].value() === "free" -> {
                hologram.appendTextLine("${ChatColor.GRAY}█ ${ChatColor.BOLD}${ChatColor.GRAY}! ${ChatColor.GRAY}█")
                hologram.appendTextLine("0 ${ChatColor.GOLD}⛃")
                loc.block.setMetadata("cost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "0"))
            }
            loc.block.getMetadata("rarity")[0].value() === "common" -> {
                hologram.appendTextLine("${ChatColor.WHITE}█ ${ChatColor.BOLD}${ChatColor.WHITE}! ${ChatColor.WHITE}█")
                hologram.appendTextLine("25 ${ChatColor.GOLD}⛃")
                loc.block.setMetadata("cost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "25"))
            }
            loc.block.getMetadata("rarity")[0].value() === "diamond" -> {
                hologram.appendTextLine("${ChatColor.AQUA}█ ${ChatColor.BOLD}${ChatColor.AQUA}! ${ChatColor.AQUA}█")
                hologram.appendTextLine("75 ${ChatColor.GOLD}⛃")
                loc.block.setMetadata("cost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "75"))
            }
            loc.block.getMetadata("rarity")[0].value() === "gold" -> {
                hologram.appendTextLine("${ChatColor.GOLD}█ ${ChatColor.BOLD}${ChatColor.GOLD}! ${ChatColor.GOLD}█")
                hologram.appendTextLine("100 ${ChatColor.GOLD}⛃")
                loc.block.setMetadata("cost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "100"))
            }
            loc.block.getMetadata("rarity")[0].value() === "emerald" -> {
                hologram.appendTextLine("${ChatColor.GREEN}█ ${ChatColor.BOLD}${ChatColor.GREEN}! ${ChatColor.GREEN}█")
                hologram.appendTextLine("300 ${ChatColor.GOLD}⛃")
                loc.block.setMetadata("cost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), "300"))
            }
        }
    }

    private fun spawnBattle(chunk: Chunk, world: World) {
        val x = ((chunk.x * 16)..((chunk.x * 16) + 16)).random()
        val z = ((chunk.z * 16)..((chunk.z * 16) + 16)).random()
        val loc: Location?
        val highestBlock = world.getHighestBlockAt(x, z).y + 1
        loc = Location(world, x.toDouble(), highestBlock.toDouble(), z.toDouble())
        loc.block.type = Material.TARGET
        val loc2 = Location(world, x.toDouble() + 0.5, highestBlock.toDouble() + 1.5, z.toDouble() + 0.5)
        val hologram = HologramsAPI.createHologram(JavaPlugin.getPlugin(Main::class.java), loc2)
        hologram.appendTextLine("Start Battle")
    }

    private fun spawnTree(chunk: Chunk, world: World) {
        val x = ((chunk.x * 16)..((chunk.x * 16) + 16)).random()
        val z = ((chunk.z * 16)..((chunk.z * 16) + 16)).random()
        val loc: Location?
        val highestBlock = world.getHighestBlockAt(x, z).y + 1
        loc = Location(world, x.toDouble(), highestBlock.toDouble(), z.toDouble())
        if (world.getHighestBlockAt(x, z).type === Material.GRASS_BLOCK) {
            loc.block.type = Material.AIR
            world.generateTree(loc, trees.random())
        }
    }

    private fun spawnOre(chunk: Chunk, world: World) {
        val x = ((chunk.x * 16)..((chunk.x * 16) + 16)).random()
        val z = ((chunk.z * 16)..((chunk.z * 16) + 16)).random()
        val loc: Location?
        val highestBlock = world.getHighestBlockAt(x, z).y + 1
        loc = Location(world, x.toDouble(), highestBlock.toDouble(), z.toDouble())
        when {
            Random.nextInt(100) > 75 -> {
                loc.block.type = Material.STONE
            }
            Random.nextInt(100) > 50 -> {
                loc.block.type = Material.IRON_ORE
            }
            Random.nextInt(100) > 25 -> {
                loc.block.type = Material.DIAMOND_ORE
            }
            Random.nextInt(100) > 24 -> {
                loc.block.type = Material.ANCIENT_DEBRIS
            }
        }
        when {
            Random.nextInt(100) > 25 -> {
                loc.block.type = Material.COAL_ORE
            }
            Random.nextInt(100) > 75 -> {
                loc.block.type = Material.GOLD_ORE
            }
        }
    }

    @EventHandler
    fun onChunkLoad(e: ChunkLoadEvent) {
        val source = e.chunk
        val world = e.world
        val random = Random
        if (e.isNewChunk) {
            if (random.nextInt(100) > 20) {
                spawnOre(source, world)
            }
            if (random.nextInt(100) > 80) {
                spawnBattle(source, world)
            }
            spawnChest(source, world)
            if (random.nextInt(100) > 90) {
                spawnTree(source, world)
            }
        }
    }
}