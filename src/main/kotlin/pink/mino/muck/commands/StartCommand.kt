package pink.mino.muck.commands

import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.jetbrains.annotations.NotNull
import pink.mino.muck.Main
import pink.mino.muck.utils.Settings

class StartCommand : CommandExecutor {

    private var taskID: Int = 0

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        if (player.isOp) {
            Settings.instance.data!!.set("started", true)
            Settings.instance.saveData()
            for (p in Bukkit.getServer().onlinePlayers) {
                p.sendMessage("${ChatColor.GREEN}Game started by ${player.name}.")
            }
            val wc = WorldCreator("Muck")
            wc.environment(World.Environment.NORMAL)
            wc.type(WorldType.NORMAL)
            wc.generateStructures(false)
            wc.createWorld()
            val world = Bukkit.getServer().getWorld("Muck")
            if (world != null) {
                world.pvp = false
            }
            for (p in Bukkit.getServer().onlinePlayers) {
                p.sendMessage("${ChatColor.GREEN}Generating a world... please wait 10 seconds.")
            }
            Bukkit.getServer().scheduler.scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main::class.java), {
                val list = ArrayList<String>()
                for (p in Bukkit.getServer().onlinePlayers) {
                    if (world != null) {
                        list.add(p.name)
                        p.teleport(world.spawnLocation)
                        p.sendTitle("${ChatColor.WHITE}${ChatColor.BOLD}- DAY 0 -", null)
                        for (e in world.entities) {
                            if (e.type !== EntityType.PLAYER && e.type !== EntityType.ARMOR_STAND) e.remove()
                        }
                    }
                }
                Settings.instance.data!!.set("list", list)
                Settings.instance.saveData()
                runGameTimer()
            }, 200)
            return true
        } else {
            player.sendMessage("${ChatColor.RED}You don't have permission to use this command.")
            return false
        }
    }

    private fun runGameTimer() {
        val world = Bukkit.getWorld("Muck")
        taskID = Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main::class.java) /* ? */, {
            val started = Settings.instance.data!!.get("started") as Boolean
            if (started) {
                if (world != null) {
                    val day = world.fullTime / 24000
                    if (world.time == 1L) {
                        for (p in Bukkit.getServer().onlinePlayers) {
                            p.sendTitle("${ChatColor.WHITE}${ChatColor.BOLD}- DAY $day -", null)
                        }
                    }
                }
            } else {
                cancelGameTimer()
            }
        }, 0, 1)
    }

    private fun cancelGameTimer() {
        Bukkit.getServer().scheduler.cancelTask(taskID)
        val spawn = Location(Bukkit.getServer().getWorld("Lobby"), 0.5, 36.5, 0.5)
        for (p in Bukkit.getServer().onlinePlayers) {
            p.sendMessage("${ChatColor.DARK_RED}Game over! Sending all players to spawn!")
            p.sendMessage("${ChatColor.DARK_RED}The server will restart in 5 seconds...")
            val effects = p.activePotionEffects
            for (effect in effects) {
                p.removePotionEffect(effect.type)
            }
            val inv = p.inventory

            // clear main inventory
            inv.clear()

            // clear armour slots
            inv.setArmorContents(null)
            p.setItemOnCursor(ItemStack(Material.AIR))

            val openInventory = p.openInventory;
            if (openInventory.type == InventoryType.CRAFTING) {
                openInventory.topInventory.clear()
            }
            p.exp = 0.0F
            p.level = 0
            p.gameMode = GameMode.ADVENTURE
            p.isFlying = false
            p.health = 10.0
            p.foodLevel = 10
            // Add a reset powerups thingy.
            p.teleport(spawn)
        }
        Bukkit.getServer().scheduler.scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main::class.java), {
            Bukkit.getServer().shutdown()
        }, 100)
    }
}