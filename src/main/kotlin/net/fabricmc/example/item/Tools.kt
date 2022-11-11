package net.fabricmc.example.item

import net.minecraft.block.BlockState
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


class MySwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float) :
    SwordItem(material, attackDamage, attackSpeed, Settings().group(ItemGroup.COMBAT)) {
    override fun postMine(
        stack: ItemStack,
        world: World,
        state: BlockState,
        pos: BlockPos,
        miner: LivingEntity
    ): Boolean {
        if (!world.isClient) {
            val hardness = state.getHardness(world, pos)
            if (hardness != 0.0f) {
                stack.damage(hardness.toInt().coerceAtLeast(1) * 2, miner) { e: LivingEntity ->
                    e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)
                }
            }
        }
        return true
    }
}

private fun toolPostMine(
    stack: ItemStack,
    world: World,
    state: BlockState,
    pos: BlockPos,
    miner: LivingEntity
): Boolean {
    if (!world.isClient) {
        val hardness = state.getHardness(world, pos)
        if (hardness != 0.0f) {
            stack.damage(hardness.toInt().coerceAtLeast(1), miner) { e: LivingEntity ->
                e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)
            }
        }
    }
    return true
}

class MyPickaxeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float) :
    PickaxeItem(material, attackDamage, attackSpeed, Settings().group(ItemGroup.TOOLS)) {
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

class MyShovelItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float) :
    ShovelItem(material, attackDamage.toFloat(), attackSpeed, Settings().group(ItemGroup.TOOLS)) {
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

class MyAxeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float) :
    AxeItem(material, attackDamage.toFloat(), attackSpeed, Settings().group(ItemGroup.TOOLS)) {
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
