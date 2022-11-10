package net.fabricmc.example.item

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

enum class ToolMaterials(
    private val miningLevel: Int,
    private val durabilityMultiplier: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: () -> Ingredient,
) : ToolMaterial {
    GOLD(0, 1, 12.0f, 0.0f, 22, { Ingredient.ofItems(Items.GOLD_INGOT) }),

    COPPER(1, 1, 2.0f, 0.0f, 5, { Ingredient.ofItems(Items.COPPER_INGOT) }),
    IRON(2, 2, 4.0f, 1.0f, 10, { Ingredient.ofItems(Items.IRON_INGOT) }),
    MITHRIL(3, 4, 6.0f, 2.0f, 15, { Ingredient.ofItems(MITHRIL_INGOT) }),
    ADAMANTITE(4, 16, 8.0f, 3.0f, 20, { Ingredient.ofItems(ADAMANTITE_INGOT) });

    override fun getDurability(): Int = durabilityMultiplier * BASE_DURABILITY
    override fun getMiningSpeedMultiplier(): Float = miningSpeed
    override fun getAttackDamage(): Float = attackDamage
    override fun getMiningLevel(): Int = miningLevel
    override fun getEnchantability(): Int = enchantability
    override fun getRepairIngredient(): Ingredient = repairIngredient()

    companion object {
        private const val BASE_DURABILITY = 80
    }
}

private val COPPER_SWORD = MySwordItem(ToolMaterials.COPPER, 4, -2.8F)
private val COPPER_PICKAXE = MyPickaxeItem(ToolMaterials.COPPER, 2f, -2.8f)
private val COPPER_SHOVEL = MyShovelItem(ToolMaterials.COPPER, 1f, -3f)
private val COPPER_AXE = MyAxeItem(ToolMaterials.COPPER, 3f, -3f)
private val COPPER_HOE = MyHoeItem(ToolMaterials.COPPER, 1, -3f)

private val IRON_SWORD = MySwordItem(ToolMaterials.IRON, 4, -2.8F)
private val IRON_PICKAXE = MyPickaxeItem(ToolMaterials.IRON, 2f, -2.8f)
private val IRON_SHOVEL = MyShovelItem(ToolMaterials.IRON, 1f, -3f)
private val IRON_AXE = MyAxeItem(ToolMaterials.IRON, 3f, -3f)
private val IRON_HOE = MyHoeItem(ToolMaterials.IRON, 1, -3f)

private val MITHRIL_SWORD = MySwordItem(ToolMaterials.MITHRIL, 4, -2.4F)
private val MITHRIL_PICKAXE = MyPickaxeItem(ToolMaterials.MITHRIL, 2f, -2.8f)
private val MITHRIL_SHOVEL = MyShovelItem(ToolMaterials.MITHRIL, 1f, -3f)
private val MITHRIL_AXE = MyAxeItem(ToolMaterials.MITHRIL, 3f, -3f)
private val MITHRIL_HOE = MyHoeItem(ToolMaterials.MITHRIL, 1, -3f)

private val ADAMANTITE_SWORD = MySwordItem(ToolMaterials.ADAMANTITE, 4, -2.4F)
private val ADAMANTITE_PICKAXE = MyPickaxeItem(ToolMaterials.ADAMANTITE, 2f, -2.8f)
private val ADAMANTITE_SHOVEL = MyShovelItem(ToolMaterials.ADAMANTITE, 1f, -3f)
private val ADAMANTITE_AXE = MyAxeItem(ToolMaterials.ADAMANTITE, 3f, -3f)
private val ADAMANTITE_HOE = MyHoeItem(ToolMaterials.ADAMANTITE, 1, -3f)

val toolItems = listOf<Pair<String, Item>>(
    "tools/copper_sword" to COPPER_SWORD,
    "tools/copper_pickaxe" to COPPER_PICKAXE,
    "tools/copper_shovel" to COPPER_SHOVEL,
    "tools/copper_axe" to COPPER_AXE,
    "tools/copper_hoe" to COPPER_HOE,

    "tools/iron_sword" to IRON_SWORD,
    "tools/iron_pickaxe" to IRON_PICKAXE,
    "tools/iron_shovel" to IRON_SHOVEL,
    "tools/iron_axe" to IRON_AXE,
    "tools/iron_hoe" to IRON_HOE,

    "tools/mithril_sword" to MITHRIL_SWORD,
    "tools/mithril_pickaxe" to MITHRIL_PICKAXE,
    "tools/mithril_shovel" to MITHRIL_SHOVEL,
    "tools/mithril_axe" to MITHRIL_AXE,
    "tools/mithril_hoe" to MITHRIL_HOE,

    "tools/adamantite_sword" to ADAMANTITE_SWORD,
    "tools/adamantite_pickaxe" to ADAMANTITE_PICKAXE,
    "tools/adamantite_shovel" to ADAMANTITE_SHOVEL,
    "tools/adamantite_axe" to ADAMANTITE_AXE,
    "tools/adamantite_hoe" to ADAMANTITE_HOE,
)
