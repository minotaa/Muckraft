package pink.mino.muck

import org.bukkit.*
import org.bukkit.Bukkit.getServer
import org.bukkit.inventory.*
import org.bukkit.plugin.java.JavaPlugin
import pink.mino.muck.commands.StartCommand
import pink.mino.muck.listeners.*
import pink.mino.muck.utils.Settings


class Main : JavaPlugin() {
    private val settings: Settings = Settings.instance

    companion object {
        val recipeKeys: ArrayList<NamespacedKey> = ArrayList()

        fun registerRecipes() {
            getServer().recipeIterator().forEachRemaining { recipe: Recipe? ->
                when (recipe) {
                    is ShapelessRecipe -> {
                        recipeKeys.add(recipe.key)
                    }
                    is ShapedRecipe -> {
                        recipeKeys.add(recipe.key)
                    }
                    is BlastingRecipe -> {
                        recipeKeys.add(recipe.key)
                    }
                    is SmokingRecipe -> {
                        recipeKeys.add(recipe.key)
                    }
                }
            }
        }
    }

    override fun onDisable() {
        logger.info("Muck plugin disabled!")
    }
    override fun onEnable() {
        Bukkit.getServer().pluginManager.registerEvents(ChunkLoad(), this)
        Bukkit.getServer().pluginManager.registerEvents(WorldInit(), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerInteract(), this)
        Bukkit.getServer().pluginManager.registerEvents(BlockBreak(), this)
        Bukkit.getServer().pluginManager.registerEvents(BlockPlace(), this)
        Bukkit.getServer().pluginManager.registerEvents(BlockDamage(), this)
        Bukkit.getServer().pluginManager.registerEvents(BlockCook(), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerDamage(), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerDeath(), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerRespawn(), this)
        Bukkit.getServer().pluginManager.registerEvents(EntityDeath(), this)
        Bukkit.getServer().pluginManager.registerEvents(CreatureSpawn(), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerJoin(), this)
        Bukkit.getServer().pluginManager.registerEvents(WeatherChange(), this)

        this.getCommand("start")?.setExecutor(StartCommand())
        Bukkit.clearRecipes()

        addRecipes()
        Bukkit.getServer().scheduler.scheduleSyncDelayedTask(this /* ? */, Runnable breakout@{
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "mvimport Muck normal"
            )
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "mvdelete Muck"
            )
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "mvconfirm"
            )
        }, 60)

        settings.setup(this)
        settings.data!!.set("started", false)
        settings.data!!.set("list", null)
        settings.saveData()
        logger.info("Muck plugin enabled!")
    }
    private fun addRecipes() {
        var item = ItemStack(Material.WOODEN_PICKAXE)
        item.itemMeta.isUnbreakable = true
        var key = NamespacedKey(this, "wooden_pickaxe")
        val woodenPickaxe = ShapelessRecipe(key, item)
        woodenPickaxe.addIngredient(Material.OAK_PLANKS)
        woodenPickaxe.addIngredient(Material.OAK_PLANKS)
        woodenPickaxe.addIngredient(Material.OAK_PLANKS)
        woodenPickaxe.addIngredient(Material.OAK_PLANKS)
        woodenPickaxe.addIngredient(Material.OAK_PLANKS)
        Bukkit.addRecipe(woodenPickaxe)

        item = ItemStack(Material.STONE_PICKAXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "stone_pickaxe")
        val stonePickaxe = ShapelessRecipe(key, item)
        stonePickaxe.addIngredient(Material.COBBLESTONE)
        stonePickaxe.addIngredient(Material.COBBLESTONE)
        stonePickaxe.addIngredient(Material.COBBLESTONE)
        stonePickaxe.addIngredient(Material.BIRCH_PLANKS)
        stonePickaxe.addIngredient(Material.BIRCH_PLANKS)
        Bukkit.addRecipe(stonePickaxe)

        item = ItemStack(Material.IRON_PICKAXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_pickaxe")
        val ironPickaxe = ShapelessRecipe(key, item)
        ironPickaxe.addIngredient(Material.IRON_INGOT)
        ironPickaxe.addIngredient(Material.IRON_INGOT)
        ironPickaxe.addIngredient(Material.IRON_INGOT)
        ironPickaxe.addIngredient(Material.ACACIA_PLANKS)
        ironPickaxe.addIngredient(Material.ACACIA_PLANKS)
        Bukkit.addRecipe(ironPickaxe)

        item = ItemStack(Material.DIAMOND_PICKAXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_pickaxe")
        val diamondPickaxe = ShapelessRecipe(key, item)
        diamondPickaxe.addIngredient(Material.DIAMOND)
        diamondPickaxe.addIngredient(Material.DIAMOND)
        diamondPickaxe.addIngredient(Material.DIAMOND)
        diamondPickaxe.addIngredient(Material.SPRUCE_PLANKS)
        diamondPickaxe.addIngredient(Material.SPRUCE_PLANKS)
        Bukkit.addRecipe(diamondPickaxe)

        item = ItemStack(Material.NETHERITE_PICKAXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_pickaxe")
        val netheritePickaxe = ShapelessRecipe(key, item)
        netheritePickaxe.addIngredient(Material.NETHERITE_INGOT)
        netheritePickaxe.addIngredient(Material.NETHERITE_INGOT)
        netheritePickaxe.addIngredient(Material.NETHERITE_INGOT)
        netheritePickaxe.addIngredient(Material.DARK_OAK_PLANKS)
        netheritePickaxe.addIngredient(Material.DARK_OAK_PLANKS)
        Bukkit.addRecipe(netheritePickaxe)

        item = ItemStack(Material.WOODEN_AXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "wooden_axe")
        val woodenAxe = ShapelessRecipe(key, item)
        woodenAxe.addIngredient(Material.OAK_PLANKS)
        woodenAxe.addIngredient(Material.OAK_PLANKS)
        woodenAxe.addIngredient(Material.OAK_PLANKS)
        woodenAxe.addIngredient(Material.OAK_PLANKS)
        woodenAxe.addIngredient(Material.OAK_PLANKS)
        woodenAxe.addIngredient(Material.OAK_PLANKS)
        Bukkit.addRecipe(woodenAxe)


        item = ItemStack(Material.STONE_AXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "stone_axe")
        val stoneAxe = ShapelessRecipe(key, item)
        stoneAxe.addIngredient(Material.COBBLESTONE)
        stoneAxe.addIngredient(Material.COBBLESTONE)
        stoneAxe.addIngredient(Material.COBBLESTONE)
        stoneAxe.addIngredient(Material.COBBLESTONE)
        stoneAxe.addIngredient(Material.BIRCH_PLANKS)
        stoneAxe.addIngredient(Material.BIRCH_PLANKS)
        Bukkit.addRecipe(stoneAxe)

        item = ItemStack(Material.IRON_AXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_axe")
        val ironAxe = ShapelessRecipe(key, item)
        ironAxe.addIngredient(Material.IRON_INGOT)
        ironAxe.addIngredient(Material.IRON_INGOT)
        ironAxe.addIngredient(Material.IRON_INGOT)
        ironAxe.addIngredient(Material.IRON_INGOT)
        ironAxe.addIngredient(Material.ACACIA_PLANKS)
        ironAxe.addIngredient(Material.ACACIA_PLANKS)
        Bukkit.addRecipe(ironAxe)

        item = ItemStack(Material.DIAMOND_AXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_axe")
        val diamondAxe = ShapelessRecipe(key, item)
        diamondAxe.addIngredient(Material.DIAMOND)
        diamondAxe.addIngredient(Material.DIAMOND)
        diamondAxe.addIngredient(Material.DIAMOND)
        diamondAxe.addIngredient(Material.DIAMOND)
        diamondAxe.addIngredient(Material.SPRUCE_PLANKS)
        diamondAxe.addIngredient(Material.SPRUCE_PLANKS)
        Bukkit.addRecipe(diamondAxe)

        item = ItemStack(Material.NETHERITE_AXE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_axe")
        val netheriteAxe = ShapelessRecipe(key, item)
        netheriteAxe.addIngredient(Material.NETHERITE_INGOT)
        netheriteAxe.addIngredient(Material.NETHERITE_INGOT)
        netheriteAxe.addIngredient(Material.NETHERITE_INGOT)
        netheriteAxe.addIngredient(Material.NETHERITE_INGOT)
        netheriteAxe.addIngredient(Material.DARK_OAK_PLANKS)
        netheriteAxe.addIngredient(Material.DARK_OAK_PLANKS)
        Bukkit.addRecipe(netheriteAxe)

        item = ItemStack(Material.STONE_SWORD)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "stone_sword")
        val stoneSword = ShapelessRecipe(key, item)
        stoneAxe.addIngredient(Material.COBBLESTONE)
        stoneAxe.addIngredient(Material.COBBLESTONE)
        stoneAxe.addIngredient(Material.BIRCH_PLANKS)
        Bukkit.addRecipe(stoneSword)

        item = ItemStack(Material.IRON_SWORD)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_sword")
        val ironSword = ShapelessRecipe(key, item)
        ironAxe.addIngredient(Material.IRON_INGOT)
        ironAxe.addIngredient(Material.IRON_INGOT)
        ironAxe.addIngredient(Material.ACACIA_PLANKS)
        Bukkit.addRecipe(ironSword)

        item = ItemStack(Material.DIAMOND_SWORD)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_sword")
        val diamondSword = ShapelessRecipe(key, item)
        diamondAxe.addIngredient(Material.DIAMOND)
        diamondAxe.addIngredient(Material.DIAMOND)
        diamondAxe.addIngredient(Material.SPRUCE_PLANKS)
        Bukkit.addRecipe(diamondSword)

        item = ItemStack(Material.NETHERITE_SWORD)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_sword")
        val netheriteSword = ShapelessRecipe(key, item)
        netheriteAxe.addIngredient(Material.NETHERITE_INGOT)
        netheriteAxe.addIngredient(Material.NETHERITE_INGOT)
        netheriteAxe.addIngredient(Material.DARK_OAK_PLANKS)
        Bukkit.addRecipe(netheriteSword)

        key = NamespacedKey(this, "furnace")
        val blastFurnace = ShapelessRecipe(key, ItemStack(Material.BLAST_FURNACE))
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        blastFurnace.addIngredient(Material.COBBLESTONE)
        Bukkit.addRecipe(blastFurnace)

        key = NamespacedKey(this, "cooker")
        val cooker = ShapelessRecipe(key, ItemStack(Material.SMOKER))
        cooker.addIngredient(Material.COBBLESTONE)
        cooker.addIngredient(Material.COBBLESTONE)
        cooker.addIngredient(Material.COBBLESTONE)
        cooker.addIngredient(Material.COBBLESTONE)
        cooker.addIngredient(Material.COBBLESTONE)
        cooker.addIngredient(Material.COBBLESTONE)
        Bukkit.addRecipe(cooker)

        item = ItemStack(Material.NETHERITE_HELMET)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_helmet")
        val netheriteHelmet = ShapelessRecipe(key, item)
        netheriteHelmet.addIngredient(Material.NETHERITE_INGOT)
        netheriteHelmet.addIngredient(Material.NETHERITE_INGOT)
        netheriteHelmet.addIngredient(Material.NETHERITE_INGOT)
        netheriteHelmet.addIngredient(Material.NETHERITE_INGOT)
        netheriteHelmet.addIngredient(Material.NETHERITE_INGOT)
        Bukkit.addRecipe(netheriteHelmet)

        item = ItemStack(Material.NETHERITE_CHESTPLATE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_chestplate")
        val netheriteChestplate = ShapelessRecipe(key, item)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        netheriteChestplate.addIngredient(Material.NETHERITE_INGOT)
        Bukkit.addRecipe(netheriteChestplate)

        item = ItemStack(Material.NETHERITE_LEGGINGS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_leggings")
        val netheriteLeggings = ShapelessRecipe(key, item)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        netheriteLeggings.addIngredient(Material.NETHERITE_INGOT)
        Bukkit.addRecipe(netheriteLeggings)

        item = ItemStack(Material.NETHERITE_BOOTS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "netherite_boots")
        val netheriteBoots = ShapelessRecipe(key, item)
        netheriteBoots.addIngredient(Material.NETHERITE_INGOT)
        netheriteBoots.addIngredient(Material.NETHERITE_INGOT)
        netheriteBoots.addIngredient(Material.NETHERITE_INGOT)
        netheriteBoots.addIngredient(Material.NETHERITE_INGOT)
        Bukkit.addRecipe(netheriteBoots)

        item = ItemStack(Material.DIAMOND_HELMET)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_helmet")
        val diamondHelmet = ShapelessRecipe(key, item)
        diamondHelmet.addIngredient(Material.DIAMOND)
        diamondHelmet.addIngredient(Material.DIAMOND)
        diamondHelmet.addIngredient(Material.DIAMOND)
        diamondHelmet.addIngredient(Material.DIAMOND)
        diamondHelmet.addIngredient(Material.DIAMOND)
        Bukkit.addRecipe(diamondHelmet)

        item = ItemStack(Material.DIAMOND_CHESTPLATE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_chestplate")
        val diamondChestplate = ShapelessRecipe(key, item)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        diamondChestplate.addIngredient(Material.DIAMOND)
        Bukkit.addRecipe(diamondChestplate)

        item = ItemStack(Material.DIAMOND_LEGGINGS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_leggings")
        val diamondLeggings = ShapelessRecipe(key, item)
        diamondLeggings.addIngredient(Material.DIAMOND)
        diamondLeggings.addIngredient(Material.DIAMOND)
        diamondLeggings.addIngredient(Material.DIAMOND)
        diamondLeggings.addIngredient(Material.DIAMOND)
        diamondLeggings.addIngredient(Material.DIAMOND)
        diamondLeggings.addIngredient(Material.DIAMOND)
        diamondLeggings.addIngredient(Material.DIAMOND)
        Bukkit.addRecipe(diamondLeggings)

        item = ItemStack(Material.DIAMOND_BOOTS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "diamond_boots")
        val diamondBoots = ShapelessRecipe(key, item)
        diamondBoots.addIngredient(Material.DIAMOND)
        diamondBoots.addIngredient(Material.DIAMOND)
        diamondBoots.addIngredient(Material.DIAMOND)
        diamondBoots.addIngredient(Material.DIAMOND)
        Bukkit.addRecipe(diamondBoots)

        item = ItemStack(Material.IRON_HELMET)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_helmet")
        val ironHelmet = ShapelessRecipe(key, item)
        ironHelmet.addIngredient(Material.IRON_INGOT)
        ironHelmet.addIngredient(Material.IRON_INGOT)
        ironHelmet.addIngredient(Material.IRON_INGOT)
        ironHelmet.addIngredient(Material.IRON_INGOT)
        ironHelmet.addIngredient(Material.IRON_INGOT)
        Bukkit.addRecipe(ironHelmet)

        item = ItemStack(Material.IRON_CHESTPLATE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_chestplate")
        val ironChestplate = ShapelessRecipe(key, item)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        ironChestplate.addIngredient(Material.IRON_INGOT)
        Bukkit.addRecipe(ironChestplate)

        item = ItemStack(Material.IRON_LEGGINGS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_leggings")
        val ironLeggings = ShapelessRecipe(key, item)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        ironLeggings.addIngredient(Material.IRON_INGOT)
        Bukkit.addRecipe(ironLeggings)

        item = ItemStack(Material.IRON_BOOTS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "iron_boots")
        val ironBoots = ShapelessRecipe(key, item)
        ironBoots.addIngredient(Material.IRON_INGOT)
        ironBoots.addIngredient(Material.IRON_INGOT)
        ironBoots.addIngredient(Material.IRON_INGOT)
        ironBoots.addIngredient(Material.IRON_INGOT)
        Bukkit.addRecipe(ironBoots)

        item = ItemStack(Material.CHAINMAIL_HELMET)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "chainmail_helmet")
        val chainmailHelmet = ShapelessRecipe(key, item)
        chainmailHelmet.addIngredient(Material.STONE)
        chainmailHelmet.addIngredient(Material.STONE)
        chainmailHelmet.addIngredient(Material.STONE)
        chainmailHelmet.addIngredient(Material.STONE)
        chainmailHelmet.addIngredient(Material.STONE)
        Bukkit.addRecipe(chainmailHelmet)

        item = ItemStack(Material.CHAINMAIL_CHESTPLATE)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "chainmail_chestplate")
        val chainmailChestplate = ShapelessRecipe(key, item)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        chainmailChestplate.addIngredient(Material.STONE)
        Bukkit.addRecipe(chainmailChestplate)

        item = ItemStack(Material.CHAINMAIL_LEGGINGS)
        item.itemMeta.isUnbreakable = true
        key = NamespacedKey(this, "chainmail_leggings")
        val chainmailLeggings = ShapelessRecipe(key, item)
        chainmailLeggings.addIngredient(Material.STONE)
        chainmailLeggings.addIngredient(Material.STONE)
        chainmailLeggings.addIngredient(Material.STONE)
        chainmailLeggings.addIngredient(Material.STONE)
        chainmailLeggings.addIngredient(Material.STONE)
        chainmailLeggings.addIngredient(Material.STONE)
        chainmailLeggings.addIngredient(Material.STONE)
        Bukkit.addRecipe(chainmailLeggings)

        key = NamespacedKey(this, "chainmail_boots")
        item = ItemStack(Material.CHAINMAIL_BOOTS)
        item.itemMeta.isUnbreakable = true
        val chainmailBoots = ShapelessRecipe(key, item)
        chainmailBoots.addIngredient(Material.STONE)
        chainmailBoots.addIngredient(Material.STONE)
        chainmailBoots.addIngredient(Material.STONE)
        chainmailBoots.addIngredient(Material.STONE)
        Bukkit.addRecipe(chainmailBoots)

        key = NamespacedKey(this, "stick")
        val stick = ShapelessRecipe(key, ItemStack(Material.STICK, 4))
        stick.addIngredient(Material.OAK_PLANKS)
        stick.addIngredient(Material.OAK_PLANKS)
        Bukkit.addRecipe(stick)

        key = NamespacedKey(this, "crafting_table")
        val craftingTable = ShapelessRecipe(key, ItemStack(Material.CRAFTING_TABLE))
        craftingTable.addIngredient(Material.OAK_PLANKS)
        craftingTable.addIngredient(Material.OAK_PLANKS)
        craftingTable.addIngredient(Material.OAK_PLANKS)
        craftingTable.addIngredient(Material.OAK_PLANKS)
        Bukkit.addRecipe(craftingTable)

        key = NamespacedKey(this, "wood")
        val wood = ShapelessRecipe(key, ItemStack(Material.OAK_PLANKS, 4))
        wood.addIngredient(Material.OAK_LOG)
        Bukkit.addRecipe(wood)

        key = NamespacedKey(this, "birch_wood")
        val birch = ShapelessRecipe(key, ItemStack(Material.BIRCH_PLANKS, 4))
        birch.addIngredient(Material.BIRCH_LOG)
        Bukkit.addRecipe(birch)

        key = NamespacedKey(this, "acacia_wood")
        val acacia = ShapelessRecipe(key, ItemStack(Material.ACACIA_PLANKS, 4))
        acacia.addIngredient(Material.ACACIA_LOG)
        Bukkit.addRecipe(acacia)

        key = NamespacedKey(this, "spruce_wood")
        val spruce = ShapelessRecipe(key, ItemStack(Material.SPRUCE_PLANKS, 4))
        spruce.addIngredient(Material.SPRUCE_LOG)
        Bukkit.addRecipe(spruce)

        key = NamespacedKey(this, "dark_oak_wood")
        val darkOak = ShapelessRecipe(key, ItemStack(Material.DARK_OAK_PLANKS, 4))
        darkOak.addIngredient(Material.DARK_OAK_LOG)
        Bukkit.addRecipe(darkOak)

        key = NamespacedKey(this, "torch")
        val torch = ShapelessRecipe(key, ItemStack(Material.TORCH, 4))
        wood.addIngredient(Material.COAL)
        wood.addIngredient(Material.STICK)
        Bukkit.addRecipe(torch)

        key = NamespacedKey(this, "netherite_ingot")
        val netheriteIngot = BlastingRecipe(key, ItemStack(Material.NETHERITE_INGOT), Material.ANCIENT_DEBRIS, 10.0F, 100)
        Bukkit.addRecipe(netheriteIngot)
        key = NamespacedKey(this, "diamond_ingot")
        val diamond = BlastingRecipe(key, ItemStack(Material.DIAMOND), Material.DIAMOND_ORE, 10.0F, 100)
        Bukkit.addRecipe(diamond)
        key = NamespacedKey(this, "iron_ingot")
        val ironIngot = BlastingRecipe(key, ItemStack(Material.IRON_INGOT), Material.IRON_ORE, 10.0F, 100)
        Bukkit.addRecipe(ironIngot)
        key = NamespacedKey(this, "stone")
        val stone = BlastingRecipe(key, ItemStack(Material.STONE), Material.COBBLESTONE, 10.0F, 100)
        Bukkit.addRecipe(stone)

        key = NamespacedKey(this, "cooked_beef")
        val cookedBeef = SmokingRecipe(key, ItemStack(Material.COOKED_BEEF), Material.BEEF, 10.0F, 100)
        Bukkit.addRecipe(cookedBeef)

    }
}