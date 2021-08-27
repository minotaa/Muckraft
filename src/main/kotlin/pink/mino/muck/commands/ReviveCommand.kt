package pink.mino.muck.commands

import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import pink.mino.muck.utils.Settings

class ReviveCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        val started = Settings.instance.data!!.get("started") as Boolean
        if (!started) {
            player.sendMessage("${ChatColor.RED}The game hasn't started, please try this command while a game is running.")
            return false
        }
        val list = Settings.instance.data!!.get("list") as ArrayList<*>
        val deadPlayers: ArrayList<String> = arrayListOf()
        for (p in Bukkit.getOnlinePlayers()) {
            if (!list.contains(p.name)) {
                deadPlayers.add(p.name)
            }
        }
        if (!deadPlayers.contains(args[0])) {
            player.sendMessage("${ChatColor.RED}The provided player is not a valid dead player.")
            return false
        }
        var amount = 0
        for (item in player.inventory.contents) {
            if (item != null) {
                if (item.type === Material.GOLD_INGOT) amount += item.amount
            }
        }
        val world = Bukkit.getWorld("Muck")
        var day = 0L
        if (world !== null) {
            day = world.fullTime / 24000
        }
        if (amount < (50 * (day + 1))) {
            player.sendMessage("${ChatColor.RED}You can't afford this, you need ${ChatColor.WHITE}${(50 * (day + 1)) - amount} ${ChatColor.GOLD}⛃${ChatColor.RED} more gold.")
            return false
        }
        val deadPlayer = Bukkit.getPlayer(args[0])
        val x: Double = Settings.instance.data!!.get("death.${deadPlayer!!.name}.x") as Double
        val y: Double = Settings.instance.data!!.get("death.${deadPlayer.name}.y") as Double
        val z: Double = Settings.instance.data!!.get("death.${deadPlayer.name}.z") as Double
        val location = Location(world, x, y, z)
        deadPlayer.teleport(location)
        deadPlayer.gameMode = GameMode.SURVIVAL
        deadPlayer.sendMessage("${ChatColor.GREEN}You have been revived!")
        player.inventory.removeItem(ItemStack(Material.GOLD_INGOT, (50 * (day + 1)).toInt()))
        return true
    }

}