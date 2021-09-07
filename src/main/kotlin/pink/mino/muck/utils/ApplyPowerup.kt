package pink.mino.muck.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import pink.mino.muck.Main
import pink.mino.muck.utils.powerups.CommonPowerups
import pink.mino.muck.utils.powerups.LegendaryPowerups
import pink.mino.muck.utils.powerups.RarePowerups

class ApplyPowerup {
    companion object {
        fun applyPower(p: Player, powerup: Any) {
            when {
                powerup === CommonPowerups.SATURATION_LOSS_REDUCER -> {
                    if (p.hasMetadata("saturation_loss_reduction")) {
                        p.setMetadata("saturation_loss_reduction", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("saturation_loss_reduction")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("saturation_loss_reduction", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Saturation Loss Reduction${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.JUMP_BOOST -> {
                    if (p.hasMetadata("jump_boost")) {
                        p.setMetadata("jump_boost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("jump_boost")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("jump_boost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    p.removePotionEffect(PotionEffectType.JUMP)
                    p.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 999999999, p.getMetadata("jump_boost")[0].value().toString().toInt() - 1, false, false))
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Jump Boost${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.HIGHER_ATTACK_SPEED -> {
                    if (p.hasMetadata("higher_attack_speed")) {
                        p.setMetadata("higher_attack_speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("higher_attack_speed")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("higher_attack_speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    val multiplier: Double = p.getMetadata("higher_attack_speed")[0].value().toString().toInt().toDouble()
                    p.getAttribute(Attribute.GENERIC_ATTACK_SPEED)!!.baseValue = (4.0 + multiplier)
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Higher Attack Speed${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.SPEED -> {
                    if (p.hasMetadata("speed")) {
                        p.setMetadata("speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("speed")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("speed", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    val multiplier: Double = p.getMetadata("speed")[0].value().toString().toInt().toDouble()
                    p.walkSpeed = (0.2 + (0.01 * multiplier)).toFloat()
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Speed${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.HEALTH_REGEN -> {
                    if (p.hasMetadata("health_regen")) {
                        p.setMetadata("health_regen", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("health_regen")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("health_regen", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    p.removePotionEffect(PotionEffectType.REGENERATION)
                    val multiplier: Int = p.getMetadata("health_regen")[0].value().toString().toInt()
                    p.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 999999999, multiplier - 1, false, false))
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Health Regeneration${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.INCREASED_STRENGTH -> {
                    if (p.hasMetadata("strength")) {
                        p.setMetadata("strength", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("strength")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("strength", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Strength${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.HIGHER_MAX_HEALTH -> {
                    if (p.hasMetadata("higher_max_health")) {
                        p.setMetadata("higher_max_health", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("higher_max_health")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("higher_max_health", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    val multiplier: Double = p.getMetadata("higher_max_health")[0].value().toString().toInt().toDouble()
                    p.maxHealth = 10.0 + multiplier
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}More Health${ChatColor.GREEN} powerup!")
                }
                powerup === CommonPowerups.HIGHER_ABSORPTION -> {
                    if (p.hasMetadata("higher_absorption")) {
                        p.setMetadata("higher_absorption", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("higher_absorption")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("higher_absorption", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    p.removePotionEffect(PotionEffectType.ABSORPTION)
                    val multiplier: Int = p.getMetadata("higher_absorption")[0].value().toString().toInt()
                    p.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 999999999, multiplier - 1, false, false))
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Higher Shield${ChatColor.GREEN} powerup!")
                }
                powerup === RarePowerups.DEFENSE -> {
                    if (p.hasMetadata("defense")) {
                        p.setMetadata("defense", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("defense")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("defense", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Defense${ChatColor.GREEN} powerup!")
                }
                powerup === RarePowerups.DRACULA -> {
                    if (p.hasMetadata("dracula")) {
                        p.setMetadata("dracula", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("dracula")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("dracula", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Dracula${ChatColor.GREEN} powerup!")
                }
                powerup === RarePowerups.INCREASED_LOOT -> {
                    if (p.hasMetadata("increased_loot")) {
                        p.setMetadata("increased_loot", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("increased_loot")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("increased_loot", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Increased Loot${ChatColor.GREEN} powerup!")
                }
                powerup === RarePowerups.LIFESTEAL -> {
                    if (p.hasMetadata("lifesteal")) {
                        p.setMetadata("lifesteal", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("lifesteal")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("lifesteal", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Lifesteal${ChatColor.GREEN} powerup!")
                }
                powerup === RarePowerups.MORE_CRITICAL -> {
                    if (p.hasMetadata("more_critical")) {
                        p.setMetadata("more_critical", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("more_critical")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("more_critical", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}More Criticals${ChatColor.GREEN} powerup!")
                }
                powerup === LegendaryPowerups.ADRENALINE -> {
                    if (p.hasMetadata("adrenaline")) {
                        p.setMetadata("adrenaline", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("adrenaline")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("adrenaline", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Adrenaline${ChatColor.GREEN} powerup!")
                }
                powerup === LegendaryPowerups.BERSERK -> {
                    if (p.hasMetadata("berserk")) {
                        p.setMetadata("berserk", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("berserk")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("berserk", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Berserk${ChatColor.GREEN} powerup!")
                }
                powerup === LegendaryPowerups.HASTE -> {
                    if (p.hasMetadata("haste")) {
                        p.setMetadata("haste", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("haste")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("haste", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING)
                    val multiplier: Int = p.getMetadata("haste")[0].value().toString().toInt()
                    p.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 999999999, multiplier - 1, false, false))
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Haste${ChatColor.GREEN} powerup!")
                }
                powerup === LegendaryPowerups.SMITING -> {
                    if (p.hasMetadata("smiting")) {
                        p.setMetadata("smiting", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("smiting")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("smiting", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Smiting${ChatColor.GREEN} powerup!")
                }
                powerup === LegendaryPowerups.SPEED_BOOST -> {
                    if (p.hasMetadata("speed_boost")) {
                        p.setMetadata("speed_boost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("speed_boost")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("speed_boost", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Speed Boost${ChatColor.GREEN} powerup!")
                }
                powerup === LegendaryPowerups.SUPER_CRITS -> {
                    if (p.hasMetadata("super_crits")) {
                        p.setMetadata("super_crits", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), p.getMetadata("super_crits")[0].value().toString().toInt() + 1))
                    } else {
                        p.setMetadata("super_crits", FixedMetadataValue(JavaPlugin.getPlugin(Main::class.java), 1))
                    }
                    Players.broadcast(Bukkit.getServer().onlinePlayers, "${ChatColor.GREEN}${p.name} got the ${ChatColor.WHITE}Super Crits${ChatColor.GREEN} powerup!")
                }
            }
        }
    }
}