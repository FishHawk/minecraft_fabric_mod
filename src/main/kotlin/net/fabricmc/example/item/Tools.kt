package net.fabricmc.example.item

import net.fabricmc.example.registry.RegisterItem.ADAMANTITE_INGOT
import net.fabricmc.example.registry.RegisterItem.MITHRIL_INGOT
import net.minecraft.block.BlockState
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

fun toolPostHit(
    stack: ItemStack,
    target: LivingEntity,
    attacker: LivingEntity,
): Boolean {
    stack.damage(2, attacker) { e: LivingEntity ->
        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)
    }
    return true
}

fun toolPostMine(
    stack: ItemStack,
    world: World,
    state: BlockState,
    pos: BlockPos,
    miner: LivingEntity
): Boolean {
    if (!world.isClient) {
        val hardness = state.getHardness(world, pos)
        if (hardness != 0.0f) {
            stack.damage(hardness.toInt() + 1, miner) { e: LivingEntity ->
                e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)
            }
        }
    }
    return true
}

class MySwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float) :
    SwordItem(material, attackDamage, attackSpeed, Settings().group(ItemGroup.COMBAT)) {
    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        return toolPostMine(stack, world, state, pos, miner)
    }
}

class MyPickaxeItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float) :
    MiningToolItem(
        attackDamage,
        attackSpeed,
        material,
        BlockTags.PICKAXE_MINEABLE,
        Settings().group(ItemGroup.TOOLS)
    ) {
    override fun postHit(
        stack: ItemStack,
        target: LivingEntity,
        attacker: LivingEntity,
    ): Boolean {
        return toolPostHit(stack, target, attacker)
    }

    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        return toolPostMine(stack, world, state, pos, miner)
    }
}

class MyShovelItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float) :
    ShovelItem(material, attackDamage, attackSpeed, Settings().group(ItemGroup.TOOLS)) {
    override fun postHit(
        stack: ItemStack,
        target: LivingEntity,
        attacker: LivingEntity,
    ): Boolean {
        return toolPostHit(stack, target, attacker)
    }

    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        return toolPostMine(stack, world, state, pos, miner)
    }
}

class MyAxeItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float) :
    AxeItem(material, attackDamage, attackSpeed, Settings().group(ItemGroup.TOOLS)) {
    override fun postHit(
        stack: ItemStack,
        target: LivingEntity,
        attacker: LivingEntity,
    ): Boolean {
        return toolPostHit(stack, target, attacker)
    }

    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        return toolPostMine(stack, world, state, pos, miner)
    }
}

class MyHoeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float) :
    HoeItem(material, attackDamage, attackSpeed, Settings().group(ItemGroup.TOOLS)) {
    override fun postHit(
        stack: ItemStack,
        target: LivingEntity,
        attacker: LivingEntity,
    ): Boolean {
        return toolPostHit(stack, target, attacker)
    }

    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        return toolPostMine(stack, world, state, pos, miner)
    }
}

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
    attackDamage = 1.0f,
    enchantability = 10,
    repairIngredient = { Ingredient.ofItems(Items.IRON_INGOT) },
)

private fun mithrilToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 3,
    durability = durability,
    miningSpeed = 6.0f,
    attackDamage = 2.0f,
    enchantability = 15,
    repairIngredient = { Ingredient.ofItems(MITHRIL_INGOT) },
)

private fun adamantiteToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 4,
    durability = durability,
    miningSpeed = 8.0f,
    attackDamage = 3.0f,
    enchantability = 20,
    repairIngredient = { Ingredient.ofItems(ADAMANTITE_INGOT) }
)

private fun goldToolMaterial(durability: Int) = toolMaterial(
    miningLevel = 1,
    durability = durability,
    miningSpeed = 12.0f,
    attackDamage = 0.0f,
    enchantability = 22,
    repairIngredient = { Ingredient.ofItems(Items.GOLD_INGOT) }
)

object ToolMaterials {
    val FLINT_HATCHET = flintToolMaterial(3)
    val FLINT = flintToolMaterial(12)

    val COPPER = copperToolMaterial(1 * 80)
    val IRON = ironToolMaterial(2 * 80)
    val MITHRIL = mithrilToolMaterial(4 * 80)
    val ADAMANTITE = adamantiteToolMaterial(16 * 80)

    val GOLD = goldToolMaterial(1 * 80)
}
