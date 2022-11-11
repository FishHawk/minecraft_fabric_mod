package net.fabricmc.example.registry

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
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

    private fun blockItemVariant(block: Block): Item {
        return BlockItem(block, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    private fun stoneSetting(): FabricBlockSettings {
        return FabricBlockSettings
            .of(Material.STONE)
            .requiresTool()
    }

    private fun deepslateSetting(): FabricBlockSettings {
        return stoneSetting()
            .mapColor(MapColor.DEEPSLATE_GRAY)
            .sounds(BlockSoundGroup.DEEPSLATE)
    }

    private fun mineralBlockSetting(): FabricBlockSettings {
        return FabricBlockSettings
            .of(Material.METAL)
            .requiresTool()
            .sounds(BlockSoundGroup.METAL)
    }

    val MITHRIL_ORE = OreBlock(stoneSetting().strength(3.0f, 3.0f))
    val DEEPSLATE_MITHRIL_ORE = OreBlock(deepslateSetting().strength(4.5F, 3.0F))
    val MITHRIL_BLOCK = Block(mineralBlockSetting().mapColor(MapColor.IRON_GRAY).strength(5.0F, 6.0F))
    val RAW_MITHRIL_BLOCK = Block(stoneSetting().strength(5.0F, 6.0F))

    val MITHRIL_ORE_ITEM = blockItem(MITHRIL_ORE)
    val DEEPSLATE_MITHRIL_ORE_ITEM = blockItem(DEEPSLATE_MITHRIL_ORE)
    val MITHRIL_BLOCK_ITEM = blockItem(MITHRIL_BLOCK)
    val RAW_MITHRIL_BLOCK_ITEM = blockItem(RAW_MITHRIL_BLOCK)

    val ADAMANTITE_ORE = OreBlock(stoneSetting().strength(3.0f, 3.0f))
    val DEEPSLATE_ADAMANTITE_ORE = OreBlock(deepslateSetting().strength(4.5F, 3.0F))
    val ADAMANTITE_BLOCK = Block(mineralBlockSetting().mapColor(MapColor.IRON_GRAY).strength(5.0F, 6.0F))
    val RAW_ADAMANTITE_BLOCK = Block(stoneSetting().strength(5.0F, 6.0F))

    val ADAMANTITE_ORE_ITEM = blockItem(ADAMANTITE_ORE)
    val DEEPSLATE_ADAMANTITE_ORE_ITEM = blockItem(DEEPSLATE_ADAMANTITE_ORE)
    val ADAMANTITE_BLOCK_ITEM = blockItem(ADAMANTITE_BLOCK)
    val RAW_ADAMANTITE_BLOCK_ITEM = blockItem(RAW_ADAMANTITE_BLOCK)

    private fun anvilSetting(): FabricBlockSettings {
        return FabricBlockSettings
            .of(Material.REPAIR_STATION)
            .strength(1.0f, 1200.0f)
            .sounds(BlockSoundGroup.ANVIL)
            .mapColor(MapColor.IRON_GRAY)
    }

    val COPPER_ANVIL_DAMAGED_0 = AnvilBlock(anvilSetting())
    val COPPER_ANVIL_DAMAGED_1 = AnvilBlock(anvilSetting())
    val COPPER_ANVIL_DAMAGED_2 = AnvilBlock(anvilSetting())

    val COPPER_ANVIL_DAMAGED_0_ITEM = blockItemVariant(COPPER_ANVIL_DAMAGED_0)
    val COPPER_ANVIL_DAMAGED_1_ITEM = blockItemVariant(COPPER_ANVIL_DAMAGED_1)
    val COPPER_ANVIL_DAMAGED_2_ITEM = blockItemVariant(COPPER_ANVIL_DAMAGED_2)

    val IRON_ANVIL_DAMAGED_0 = AnvilBlock(anvilSetting())
    val IRON_ANVIL_DAMAGED_1 = AnvilBlock(anvilSetting())
    val IRON_ANVIL_DAMAGED_2 = AnvilBlock(anvilSetting())

    val IRON_ANVIL_DAMAGED_0_ITEM = blockItemVariant(IRON_ANVIL_DAMAGED_0)
    val IRON_ANVIL_DAMAGED_1_ITEM = blockItemVariant(IRON_ANVIL_DAMAGED_1)
    val IRON_ANVIL_DAMAGED_2_ITEM = blockItemVariant(IRON_ANVIL_DAMAGED_2)

    val MITHRIL_ANVIL_DAMAGED_0 = AnvilBlock(anvilSetting())
    val MITHRIL_ANVIL_DAMAGED_1 = AnvilBlock(anvilSetting())
    val MITHRIL_ANVIL_DAMAGED_2 = AnvilBlock(anvilSetting())

    val MITHRIL_ANVIL_DAMAGED_0_ITEM = blockItemVariant(MITHRIL_ANVIL_DAMAGED_0)
    val MITHRIL_ANVIL_DAMAGED_1_ITEM = blockItemVariant(MITHRIL_ANVIL_DAMAGED_1)
    val MITHRIL_ANVIL_DAMAGED_2_ITEM = blockItemVariant(MITHRIL_ANVIL_DAMAGED_2)

    val ADAMANTITE_ANVIL_DAMAGED_0 = AnvilBlock(anvilSetting())
    val ADAMANTITE_ANVIL_DAMAGED_1 = AnvilBlock(anvilSetting())
    val ADAMANTITE_ANVIL_DAMAGED_2 = AnvilBlock(anvilSetting())

    val ADAMANTITE_ANVIL_DAMAGED_0_ITEM = blockItemVariant(ADAMANTITE_ANVIL_DAMAGED_0)
    val ADAMANTITE_ANVIL_DAMAGED_1_ITEM = blockItemVariant(ADAMANTITE_ANVIL_DAMAGED_1)
    val ADAMANTITE_ANVIL_DAMAGED_2_ITEM = blockItemVariant(ADAMANTITE_ANVIL_DAMAGED_2)

    val blocks: List<Triple<String, Block, Item>>
        get() = listOf(
            Triple("mithril_ore", MITHRIL_ORE, MITHRIL_ORE_ITEM),
            Triple("deepslate_mithril_ore", DEEPSLATE_MITHRIL_ORE, DEEPSLATE_MITHRIL_ORE_ITEM),
            Triple("mithril_block", MITHRIL_BLOCK, MITHRIL_BLOCK_ITEM),
            Triple("raw_mithril_block", RAW_MITHRIL_BLOCK, RAW_MITHRIL_BLOCK_ITEM),

            Triple("adamantite_ore", ADAMANTITE_ORE, ADAMANTITE_ORE_ITEM),
            Triple("deepslate_adamantite_ore", DEEPSLATE_ADAMANTITE_ORE, DEEPSLATE_ADAMANTITE_ORE_ITEM),
            Triple("adamantite_block", ADAMANTITE_BLOCK, ADAMANTITE_BLOCK_ITEM),
            Triple("raw_adamantite_block", RAW_ADAMANTITE_BLOCK, RAW_ADAMANTITE_BLOCK_ITEM),

            Triple("anvil/copper_damaged_0", COPPER_ANVIL_DAMAGED_0, COPPER_ANVIL_DAMAGED_0_ITEM),
            Triple("anvil/copper_damaged_1", COPPER_ANVIL_DAMAGED_1, COPPER_ANVIL_DAMAGED_1_ITEM),
            Triple("anvil/copper_damaged_2", COPPER_ANVIL_DAMAGED_2, COPPER_ANVIL_DAMAGED_2_ITEM),

            Triple("anvil/iron_damaged_0", IRON_ANVIL_DAMAGED_0, IRON_ANVIL_DAMAGED_0_ITEM),
            Triple("anvil/iron_damaged_1", IRON_ANVIL_DAMAGED_1, IRON_ANVIL_DAMAGED_1_ITEM),
            Triple("anvil/iron_damaged_2", IRON_ANVIL_DAMAGED_2, IRON_ANVIL_DAMAGED_2_ITEM),

            Triple("anvil/mithril_damaged_0", MITHRIL_ANVIL_DAMAGED_0, MITHRIL_ANVIL_DAMAGED_0_ITEM),
            Triple("anvil/mithril_damaged_1", MITHRIL_ANVIL_DAMAGED_1, MITHRIL_ANVIL_DAMAGED_1_ITEM),
            Triple("anvil/mithril_damaged_2", MITHRIL_ANVIL_DAMAGED_2, MITHRIL_ANVIL_DAMAGED_2_ITEM),

            Triple("anvil/adamantite_damaged_0", ADAMANTITE_ANVIL_DAMAGED_0, ADAMANTITE_ANVIL_DAMAGED_0_ITEM),
            Triple("anvil/adamantite_damaged_1", ADAMANTITE_ANVIL_DAMAGED_1, ADAMANTITE_ANVIL_DAMAGED_1_ITEM),
            Triple("anvil/adamantite_damaged_2", ADAMANTITE_ANVIL_DAMAGED_2, ADAMANTITE_ANVIL_DAMAGED_2_ITEM),
        )

    fun registerAll() {
        for ((path, block, item) in blocks) {
            Registry.register(Registry.BLOCK, Identifier("modid", path), block)
            Registry.register(Registry.ITEM, Identifier("modid", path), item)
        }
    }
}