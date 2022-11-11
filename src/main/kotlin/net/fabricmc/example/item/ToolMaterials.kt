package net.fabricmc.example.item

import net.fabricmc.example.registry.RegisterItem
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient


private fun toolMaterial(
    miningLevel: Int,
    durability: Int,
    miningSpeed: Float,
    attackDamage: Float,
    enchantability: Int,
    repairIngredient: () -> Ingredient,
): ToolMaterial {
    return object : ToolMaterial {
        override fun getDurability(): Int = durability
        override fun getMiningSpeedMultiplier(): Float = miningSpeed
        override fun getAttackDamage(): Float = attackDamage
        override fun getMiningLevel(): Int = miningLevel
        override fun getEnchantability(): Int = enchantability
        override fun getRepairIngredient(): Ingredient = repairIngredient()
    }
}

private fun flintToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 0,
    durability = durability,
    miningSpeed = 1.0f,
    attackDamage = 0.0f,
    enchantability = 0,
    repairIngredient = { Ingredient.EMPTY },
)

private fun copperToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 1,
    durability = durability,
    miningSpeed = 2.0f,
    attackDamage = 0.0f,
    enchantability = 5,
    repairIngredient = { Ingredient.ofItems(Items.COPPER_INGOT) },
)

private fun ironToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 2,
    durability = durability,
    miningSpeed = 4.0f,
    attackDamage = 0.0f,
    enchantability = 10,
    repairIngredient = { Ingredient.ofItems(Items.IRON_INGOT) },
)

private fun mithrilToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 3,
    durability = durability,
    miningSpeed = 6.0f,
    attackDamage = 0.0f,
    enchantability = 15,
    repairIngredient = { Ingredient.ofItems(RegisterItem.MITHRIL_INGOT) },
)

private fun adamantiteToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 4,
    durability = durability,
    miningSpeed = 8.0f,
    attackDamage = 0.0f,
    enchantability = 20,
    repairIngredient = { Ingredient.ofItems(RegisterItem.ADAMANTITE_INGOT) }
)

object ToolMaterials {
    private const val FLINT_HANDMADE_BASE_DURABILITY = 3
    private const val FLINT_BASE_DURABILITY = 7
    private const val COPPER_BASE_DURABILITY = 27
    private const val IRON_BASE_DURABILITY = 27 * 2
    private const val MITHRIL_BASE_DURABILITY = 27 * 16
    private const val ADAMANTITE_BASE_DURABILITY = 27 * 64

    val FLINT_HANDMADE_1 = flintToolMaterial(FLINT_HANDMADE_BASE_DURABILITY)

    val FLINT_1 = flintToolMaterial(FLINT_BASE_DURABILITY * 1)
    val FLINT_3 = flintToolMaterial(FLINT_BASE_DURABILITY * 3)

    val COPPER_1 = copperToolMaterial(COPPER_BASE_DURABILITY * 1)
    val COPPER_2 = copperToolMaterial(COPPER_BASE_DURABILITY * 2)
    val COPPER_3 = copperToolMaterial(COPPER_BASE_DURABILITY * 3)

    val IRON_1 = ironToolMaterial(IRON_BASE_DURABILITY * 1)
    val IRON_2 = ironToolMaterial(IRON_BASE_DURABILITY * 2)
    val IRON_3 = ironToolMaterial(IRON_BASE_DURABILITY * 3)

    val MITHRIL_1 = mithrilToolMaterial(MITHRIL_BASE_DURABILITY * 1)
    val MITHRIL_2 = mithrilToolMaterial(MITHRIL_BASE_DURABILITY * 2)
    val MITHRIL_3 = mithrilToolMaterial(MITHRIL_BASE_DURABILITY * 3)

    val ADAMANTITE_1 = adamantiteToolMaterial(ADAMANTITE_BASE_DURABILITY * 1)
    val ADAMANTITE_2 = adamantiteToolMaterial(ADAMANTITE_BASE_DURABILITY * 2)
    val ADAMANTITE_3 = adamantiteToolMaterial(ADAMANTITE_BASE_DURABILITY * 3)
}
