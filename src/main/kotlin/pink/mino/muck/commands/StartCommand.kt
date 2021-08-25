package pink.mino.muck.commands

import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.jetbrains.annotations.NotNull
import pink.mino.muck.Main
import pink.mino.muck.utils.Settings

class StartCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        if (player.isOp) {
            Settings.instance.data!!.set("started", true)
            Settings.instance.saveData()
            for (p in Bukkit.getServer().onlinePlayers) {
                p.sendMessage("${ChatColor.GREEN}Game started by ${ChatColor.WHITE}${player.name}${ChatColor.GREEN}.")
            }
            val wc = WorldCreator("Muck")
            wc.environment(World.Environment.NORMAL)
            wc.type(WorldType.NORMAL)
            wc.createWorld()
            val world = Bukkit.getServer().getWorld("Muck")
            if (world != null) {
                world.pvp = false
            }
            for (p in Bukkit.getServer().onlinePlayers) {
                p.sendMessage("${ChatColor.GREEN}Generating a world... please wait 10 seconds.")
            }
            Bukkit.getServer().scheduler.scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main::class.java), {
                for (p in Bukkit.getServer().onlinePlayers) {
                    if (world != null) {
                        p.teleport(world.spawnLocation)
                    }
                }
            }, 200)
            return true
        } else {
            player.sendMessage("${ChatColor.RED}You don't have permission to use this command.")
            return false
        }
        return false
    }

    fun runDayCounter() {
        Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main::class.java) /* ? */, Runnable breakout@{
            val started = Settings.instance.data!!.get("started") as Boolean
            if (started) {

            } else {
                for (p in Bukkit.getServer().onlinePlayers) {
                    p.sendMessage("${ChatColor.DARK_RED}Game over!")
                }
                Bukkit.getServer().scheduler.cancelTask(1)
            }
        } as @NotNull BukkitRunnable, 0, 20)
    }
}