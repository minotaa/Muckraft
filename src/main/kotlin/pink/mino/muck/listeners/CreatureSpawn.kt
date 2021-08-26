package pink.mino.muck.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MerchantRecipe
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.random.Random

class CreatureSpawn : Listener {
    private val monsters: List<EntityType> = listOf(EntityType.STRAY, EntityType.HUSK, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIE, EntityType.SKELETON)
    private val jobs: List<Villager.Profession> = listOf(Villager.Profession.TOOLSMITH, Villager.Profession.ARMORER, Villager.Profession.BUTCHER, Villager.Profession.WEAPONSMITH)

    @EventHandler
    fun onEntitySpawn(e: CreatureSpawnEvent) {
        val entity = e.entity
        if (entity.type === EntityType.VILLAGER) {
            val villager = entity as Villager
            villager.profession = jobs.random()
            villager.villagerExperience = 1
            villager.recipes = listOf()
            val recipes: ArrayList<MerchantRecipe> = arrayListOf()
            when {
                villager.profession === Villager.Profession.TOOLSMITH -> {
                    var recipe = MerchantRecipe(ItemStack(Material.DIAMOND_PICKAXE), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 75))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.IRON_PICKAXE), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.STONE_PICKAXE), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 25))
                    recipes.add(recipe)
                }
                villager.profession === Villager.Profession.BUTCHER -> {
                    var recipe = MerchantRecipe(ItemStack(Material.COOKED_BEEF, 16), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)
                    recipe = MerchantRecipe(ItemStack(Material.BEETROOT_SOUP), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 10))
                    recipes.add(recipe)
                    recipe = MerchantRecipe(ItemStack(Material.MUSHROOM_STEW), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 10))
                    recipes.add(recipe)
                    recipe = MerchantRecipe(ItemStack(Material.RABBIT_STEW), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 10))
                    recipes.add(recipe)
                }
                villager.profession === Villager.Profession.ARMORER -> {
                    var recipe = MerchantRecipe(ItemStack(Material.DIAMOND_HELMET), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 75))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.DIAMOND_CHESTPLATE), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 75))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.DIAMOND_LEGGINGS), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 75))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.DIAMOND_BOOTS), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 75))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.IRON_HELMET), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.IRON_CHESTPLATE), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.IRON_LEGGINGS), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.IRON_BOOTS), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.CHAINMAIL_HELMET), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 25))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.CHAINMAIL_CHESTPLATE), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 25))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.CHAINMAIL_LEGGINGS), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 25))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.CHAINMAIL_BOOTS), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 25))
                    recipes.add(recipe)
                }
                villager.profession === Villager.Profession.WEAPONSMITH -> {
                    var recipe = MerchantRecipe(ItemStack(Material.DIAMOND_SWORD), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 75))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.IRON_SWORD), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 50))
                    recipes.add(recipe)

                    recipe = MerchantRecipe(ItemStack(Material.STONE_SWORD), 5)
                    recipe.addIngredient(ItemStack(Material.GOLD_INGOT, 25))
                    recipes.add(recipe)
                }
            }
            villager.recipes = recipes

        }
        if (entity.type !== EntityType.VILLAGER && entity.type !== EntityType.STRAY && entity.type !== EntityType.HUSK && entity.type !== EntityType.ZOMBIE_VILLAGER && entity.type !== EntityType.ZOMBIE && entity.type !== EntityType.SKELETON && entity.type !== EntityType.COW) {
            e.isCancelled = true
            if (entity.type === EntityType.CREEPER || entity.type === EntityType.SPIDER) {
                e.entity.world.spawnEntity(e.entity.location, monsters.random())
            }
        } else {
            if (entity.type !== EntityType.VILLAGER && entity.type !== EntityType.COW) {
                if (Random.nextInt(100) > 90) {
                    entity.customName = "${ChatColor.RED}☠"
                    entity.isCustomNameVisible = true
                    entity.equipment?.helmet = ItemStack(Material.LEATHER_HELMET)
                    entity.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000000, 0))
                    //if (entity.type === EntityType.SKELETON || entity.type === EntityType.STRAY) {
                    //    val bow = ItemStack(Material.BOW)
                    //    bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                    //    entity.equipment?.setItemInMainHand(bow)
                    //}
                }
            }
        }
    }
}