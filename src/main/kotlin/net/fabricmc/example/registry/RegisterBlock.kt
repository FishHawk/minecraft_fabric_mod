package net.fabricmc.example.registry

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

@Suppress("MemberVisibilityCanBePrivate")
object RegisterBlock {
    private fun blockItem(block: Block): Item {
        return BlockItem(block, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    }

    private fun stoneSetting(): FabricBlockSettings {
        return FabricBlockSettings.of(Material.STONE).requiresTool()
    }

    private fun deepslateSetting(): FabricBlockSettings {
        return stoneSetting().mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.DEEPSLATE)
    }

    private fun mineralBlockSetting(): FabricBlockSettings {
        return FabricBlockSettings.of(Material.METAL).requiresTool().sounds(BlockSoundGroup.METAL)
    }

    val MITHRIL_ORE = OreBlock(stoneSetting().strength(3.0f, 3.0f))
    val DEEPSLATE_MITHRIL_ORE = OreBlock(deepslateSetting().strength(4.5F, 3.0F))
    val MITHRIL_BLOCK = Block(mineralBlockSetting().mapColor(MapColor.IRON_GRAY).strength(5.0F, 6.0F))
    val RAW_MITHRIL_BLOCK = Block(stoneSetting().strength(5.0F, 6.0F))

    val MITHRIL_ORE_ITEM = blockItem(MITHRIL_ORE)
    val DEEPSLATE_MITHRIL_ORE_ITEM = blockItem(DEEPSLATE_MITHRIL_ORE)
    val MITHRIL_BLOCK_ITEM = blockItem(MITHRIL_BLOCK)
    val RAW_MITHRIL_BLOCK_ITEM = blockItem(RAW_MITHRIL_BLOCK)

    val blocks: List<Triple<String, Block, Item>>
        get() = listOf(
            Triple("mithril_ore", MITHRIL_ORE, MITHRIL_ORE_ITEM),
            Triple("deepslate_mithril_ore", DEEPSLATE_MITHRIL_ORE, DEEPSLATE_MITHRIL_ORE_ITEM),
            Triple("mithril_block", MITHRIL_BLOCK, MITHRIL_BLOCK_ITEM),
            Triple("raw_mithril_block", RAW_MITHRIL_BLOCK, RAW_MITHRIL_BLOCK_ITEM),
        )

    fun registerAll() {
        for ((path, block, item) in blocks) {
            Registry.register(Registry.BLOCK, Identifier("modid", path), block)
            Registry.register(Registry.ITEM, Identifier("modid", path), item)
        }
    }
}