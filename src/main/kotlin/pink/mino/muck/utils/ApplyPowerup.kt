package pink.mino.muck.utils

import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import pink.mino.muck.Main
import pink.mino.muck.utils.powerups.CommonPowerups

class ApplyPowerup {
    companion object {
        fun applyPower(p: Player, powerup: Any) {
            if (powerup === CommonPowerups.SATURATION_LOSS_REDUCER) {
                if (p.hasMetadata("saturation_loss_reduction")) {
                    p.setMetadata("saturation_loss_reduction", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("saturation_loss_reduction").toString().toInt() + 1))
                } else {
                    p.setMetadata("saturation_loss_reduction", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.JUMP_BOOST) {
                if (p.hasMetadata("jump_boost")) {
                    p.setMetadata("jump_boost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("jump_boost").toString().toInt() + 1))
                } else {
                    p.setMetadata("jump_boost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.HIGHER_ATTACK_SPEED) {
                if (p.hasMetadata("higher_attack_speed")) {
                    p.setMetadata("higher_attack_speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("higher_attack_speed").toString().toInt() + 1))
                } else {
                    p.setMetadata("higher_attack_speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.SPEED) {
                if (p.hasMetadata("speed")) {
                    p.setMetadata("speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("speed").toString().toInt() + 1))
                } else {
                    p.setMetadata("speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.HEALTH_REGEN) {
                if (p.hasMetadata("health_regen")) {
                    p.setMetadata("health_regen", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("health_regen").toString().toInt() + 1))
                } else {
                    p.setMetadata("health_regen", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.INCREASED_STRENGTH) {
                if (p.hasMetadata("strength")) {
                    p.setMetadata("strength", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("strength").toString().toInt() + 1))
                } else {
                    p.setMetadata("strength", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.HIGHER_MAX_HEALTH) {
                if (p.hasMetadata("higher_max_health")) {
                    p.setMetadata("higher_max_health", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("higher_max_health").toString().toInt() + 1))
                } else {
                    p.setMetadata("higher_max_health", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            } else if (powerup === CommonPowerups.HIGHER_ABSORPTION) {
                if (p.hasMetadata("higher_absorption")) {
                    p.setMetadata("higher_absorption", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("higher_absorption").toString().toInt() + 1))
                } else {
                    p.setMetadata("higher_absorption", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                }
            }
        }
    }
}