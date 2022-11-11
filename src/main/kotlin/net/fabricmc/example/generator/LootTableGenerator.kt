package net.fabricmc.example.generator

import net.fabricmc.example.identifier
import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.example.registry.RegisterItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.block.Block
import net.minecraft.data.server.BlockLootTableGenerator
import net.minecraft.item.Item
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

private val RegisterBlock.oreBlocks: List<Triple<String, Block, Item>>
    get() = listOf(
        Triple("mithril_ore", MITHRIL_ORE, RegisterItem.RAW_MITHRIL),
        Triple("deepslate_mithril_ore", DEEPSLATE_MITHRIL_ORE, RegisterItem.RAW_MITHRIL),

        Triple("adamantite_ore", ADAMANTITE_ORE, RegisterItem.RAW_ADAMANTITE),
        Triple("deepslate_adamantite_ore", DEEPSLATE_ADAMANTITE_ORE, RegisterItem.RAW_ADAMANTITE),
    )

private val RegisterBlock.simpleBlocks: List<Triple<String, Block, Item>>
    get() = listOf(
        Triple("mithril_block", MITHRIL_BLOCK, MITHRIL_BLOCK_ITEM),
        Triple("raw_mithril_block", RAW_MITHRIL_BLOCK, RAW_MITHRIL_BLOCK_ITEM),

        Triple("adamantite_block", ADAMANTITE_BLOCK, ADAMANTITE_BLOCK_ITEM),
        Triple("raw_adamantite_block", RAW_ADAMANTITE_BLOCK, RAW_ADAMANTITE_BLOCK_ITEM),
    )

class LootTableGenerator(dataGenerator: FabricDataGenerator) :
    SimpleFabricLootTableProvider(dataGenerator, LootContextTypes.BLOCK) {
    override fun accept(biConsumer: BiConsumer<Identifier, LootTable.Builder>) {
        for ((path, block, rawMaterial) in RegisterBlock.oreBlocks) {
            biConsumer.accept(identifier("blocks/$path"), BlockLootTableGenerator.oreDrops(block, rawMaterial))
        }
        for ((path, block, _) in RegisterBlock.simpleBlocks) {
            biConsumer.accept(identifier("blocks/$path"), BlockLootTableGenerator.drops(block))
        }
    }
}
