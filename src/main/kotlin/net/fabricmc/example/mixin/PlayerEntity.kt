package net.fabricmc.example.mixin

import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun modifyCanHarvestKt(player: PlayerEntity, state: BlockState): Boolean {
    for (identifier in listOf(
        Identifier.of(Identifier.DEFAULT_NAMESPACE, "logs"),
        Identifier.of(Identifier.DEFAULT_NAMESPACE, "planks"),
    )) {
        if (state.isIn(TagKey.of(Registry.BLOCK_KEY, identifier))) {
            return player.mainHandStack.isSuitableFor(state)
        }
    }
    return true
}
