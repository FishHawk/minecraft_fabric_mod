package net.fabricmc.example.generator

import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.tag.BlockTags
import net.minecraft.util.registry.Registry

class BlockTagGenerator(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Block>(dataGenerator, Registry.BLOCK) {
    override fun generateTags() {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(RegisterBlock.MITHRIL_ORE)
            .add(RegisterBlock.DEEPSLATE_MITHRIL_ORE)
            .add(RegisterBlock.MITHRIL_BLOCK)
            .add(RegisterBlock.RAW_MITHRIL_BLOCK)

            .add(RegisterBlock.ADAMANTITE_ORE)
            .add(RegisterBlock.DEEPSLATE_ADAMANTITE_ORE)
            .add(RegisterBlock.ADAMANTITE_BLOCK)
            .add(RegisterBlock.RAW_ADAMANTITE_BLOCK)

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(RegisterBlock.MITHRIL_ORE)
            .add(RegisterBlock.DEEPSLATE_MITHRIL_ORE)
            .add(RegisterBlock.MITHRIL_BLOCK)
            .add(RegisterBlock.RAW_MITHRIL_BLOCK)

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(RegisterBlock.ADAMANTITE_ORE)
            .add(RegisterBlock.DEEPSLATE_ADAMANTITE_ORE)
            .add(RegisterBlock.ADAMANTITE_BLOCK)
            .add(RegisterBlock.RAW_ADAMANTITE_BLOCK)

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
            .add(Blocks.COPPER_BLOCK)
            .add(RegisterBlock.MITHRIL_BLOCK)
            .add(RegisterBlock.ADAMANTITE_BLOCK)

        getOrCreateTagBuilder(BlockTags.ANVIL)
            .add(RegisterBlock.COPPER_ANVIL_DAMAGED_0)
            .add(RegisterBlock.COPPER_ANVIL_DAMAGED_1)
            .add(RegisterBlock.COPPER_ANVIL_DAMAGED_2)
            .add(RegisterBlock.IRON_ANVIL_DAMAGED_0)
            .add(RegisterBlock.IRON_ANVIL_DAMAGED_1)
            .add(RegisterBlock.IRON_ANVIL_DAMAGED_2)
            .add(RegisterBlock.MITHRIL_ANVIL_DAMAGED_0)
            .add(RegisterBlock.MITHRIL_ANVIL_DAMAGED_1)
            .add(RegisterBlock.MITHRIL_ANVIL_DAMAGED_2)
            .add(RegisterBlock.ADAMANTITE_ANVIL_DAMAGED_0)
            .add(RegisterBlock.ADAMANTITE_ANVIL_DAMAGED_1)
            .add(RegisterBlock.ADAMANTITE_ANVIL_DAMAGED_2)
    }
}