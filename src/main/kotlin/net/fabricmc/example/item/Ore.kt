package net.fabricmc.example.item

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.sound.BlockSoundGroup


val COPPER_NUGGET = Item(Item.Settings().group(ItemGroup.MISC))

object MITHRIL {
    val MITHRIL_ORE = OreBlock(
        AbstractBlock.Settings
            .of(Material.STONE)
            .requiresTool()
            .strength(3.0f, 3.0f)
    )

    val DEEPSLATE_MITHRIL_ORE = OreBlock(
        AbstractBlock.Settings
            .of(Material.STONE, MapColor.DEEPSLATE_GRAY)
            .requiresTool()
            .strength(4.5F, 3.0F)
            .sounds(BlockSoundGroup.DEEPSLATE)
    )

    val MITHRIL_BLOCK = Block(
        AbstractBlock.Settings
            .of(Material.METAL, MapColor.IRON_GRAY)
            .requiresTool()
            .strength(5.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL)
    )

    val RAW_MITHRIL_BLOCK = Block(
        AbstractBlock.Settings
            .of(Material.STONE, MapColor.RAW_IRON_PINK)
            .requiresTool()
            .strength(5.0F, 6.0F)
    )

    val MITHRIL_ORE_ITEM = BlockItem(MITHRIL_ORE, Item.Settings().group(ItemGroup.MISC))
    val DEEPSLATE_MITHRIL_ORE_ITEM = BlockItem(DEEPSLATE_MITHRIL_ORE, Item.Settings().group(ItemGroup.MISC))
    val MITHRIL_BLOCK_ITEM = BlockItem(MITHRIL_BLOCK, Item.Settings().group(ItemGroup.MISC))
    val RAW_MITHRIL_BLOCK_ITEM = BlockItem(RAW_MITHRIL_BLOCK, Item.Settings().group(ItemGroup.MISC))
}

val MITHRIL_INGOT = Item(Item.Settings().group(ItemGroup.MISC))
val MITHRIL_NUGGET = Item(Item.Settings().group(ItemGroup.MISC))
val RAW_MITHRIL = Item(Item.Settings().group(ItemGroup.MISC))

val ADAMANTITE_INGOT = Item(Item.Settings().group(ItemGroup.MISC))
val ADAMANTITE_NUGGET = Item(Item.Settings().group(ItemGroup.MISC))
val RAW_ADAMANTITE = Item(Item.Settings().group(ItemGroup.MISC))

val oreBlocks = listOf(
//    Triple("mithril_ore", MITHRIL.MITHRIL_ORE, MITHRIL.MITHRIL_ORE_ITEM),
//    Triple("deepslate_mithril_ore", MITHRIL.DEEPSLATE_MITHRIL_ORE, MITHRIL.DEEPSLATE_MITHRIL_ORE_ITEM),
    Triple("mithril_block", MITHRIL.MITHRIL_BLOCK, MITHRIL.MITHRIL_BLOCK_ITEM),
    Triple("raw_mithril_block", MITHRIL.RAW_MITHRIL_BLOCK, MITHRIL.RAW_MITHRIL_BLOCK_ITEM),
)

val oreItems = listOf(
    Pair("ingots/mithril", MITHRIL_INGOT),
    Pair("ingots/raw_mithril", RAW_MITHRIL),
    Pair("nuggets/mithril", MITHRIL_NUGGET),
)