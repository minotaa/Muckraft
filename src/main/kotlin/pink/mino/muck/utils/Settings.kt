package pink.mino.muck.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

class Settings private constructor() {
    /**
     * Gets the data config.
     * @return the config.
     */
    var data: FileConfiguration? = null
        private set
    private var dfile: File? = null

    /**
     * Sets the settings manager up and creates missing files.
     * @param p the main class.
     */
    fun setup(p: Plugin) {
        if (!p.dataFolder.exists()) {
            p.dataFolder.mkdir()
        }
        dfile = File(p.dataFolder, "data.yml")
        if (!dfile!!.exists()) {
            try {
                dfile!!.createNewFile()
            } catch (ex: IOException) {
                Bukkit.getServer().logger.severe(ChatColor.RED.toString() + "Could not create data.yml!")
            }
        }
        data = YamlConfiguration.loadConfiguration(dfile!!)
    }

    /**
     * Saves the data config.
     */
    fun saveData() {
        try {
            dfile?.let { data!!.save(it) }
        } catch (ex: IOException) {
            Bukkit.getServer().logger.severe(ChatColor.RED.toString() + "Could not save data.yml!")
        }
    }

    /**
     * Reloads the data file.
     */
    fun reloadData() {
        data = dfile?.let { YamlConfiguration.loadConfiguration(it) }
    }

    companion object {
        val instance = Settings()
    }
}