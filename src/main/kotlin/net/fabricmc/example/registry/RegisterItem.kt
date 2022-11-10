package net.fabricmc.example.registry

import net.fabricmc.example.item.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

@Suppress("MemberVisibilityCanBePrivate")
object RegisterItem {
    val COPPER_NUGGET = Item(Item.Settings().group(ItemGroup.MISC))

    val MITHRIL_INGOT = Item(Item.Settings().group(ItemGroup.MISC))
    val MITHRIL_NUGGET = Item(Item.Settings().group(ItemGroup.MISC))
    val RAW_MITHRIL = Item(Item.Settings().group(ItemGroup.MISC))

    val ADAMANTITE_INGOT = Item(Item.Settings().group(ItemGroup.MISC))
    val ADAMANTITE_NUGGET = Item(Item.Settings().group(ItemGroup.MISC))
    val RAW_ADAMANTITE = Item(Item.Settings().group(ItemGroup.MISC))

    val FLINT_HATCHET = MyAxeItem(ToolMaterials.FLINT_HATCHET, 3f, -3f)
    val FLINT_SHOVEL = MyShovelItem(ToolMaterials.FLINT, 2f, -3f)
    val FLINT_AXE = MyAxeItem(ToolMaterials.FLINT, 4f, -3f)

    val COPPER_SWORD = MySwordItem(ToolMaterials.COPPER, 4, -2.8F)
    val COPPER_PICKAXE = MyPickaxeItem(ToolMaterials.COPPER, 2f, -2.8f)
    val COPPER_SHOVEL = MyShovelItem(ToolMaterials.COPPER, 1f, -3f)
    val COPPER_AXE = MyAxeItem(ToolMaterials.COPPER, 3f, -3f)
    val COPPER_HOE = MyHoeItem(ToolMaterials.COPPER, 1, -3f)

    val IRON_SWORD = MySwordItem(ToolMaterials.IRON, 4, -2.8F)
    val IRON_PICKAXE = MyPickaxeItem(ToolMaterials.IRON, 2f, -2.8f)
    val IRON_SHOVEL = MyShovelItem(ToolMaterials.IRON, 1f, -3f)
    val IRON_AXE = MyAxeItem(ToolMaterials.IRON, 3f, -3f)
    val IRON_HOE = MyHoeItem(ToolMaterials.IRON, 1, -3f)

    val MITHRIL_SWORD = MySwordItem(ToolMaterials.MITHRIL, 4, -2.4F)
    val MITHRIL_PICKAXE = MyPickaxeItem(ToolMaterials.MITHRIL, 2f, -2.8f)
    val MITHRIL_SHOVEL = MyShovelItem(ToolMaterials.MITHRIL, 1f, -3f)
    val MITHRIL_AXE = MyAxeItem(ToolMaterials.MITHRIL, 3f, -3f)
    val MITHRIL_HOE = MyHoeItem(ToolMaterials.MITHRIL, 1, -3f)

    val ADAMANTITE_SWORD = MySwordItem(ToolMaterials.ADAMANTITE, 4, -2.4F)
    val ADAMANTITE_PICKAXE = MyPickaxeItem(ToolMaterials.ADAMANTITE, 2f, -2.8f)
    val ADAMANTITE_SHOVEL = MyShovelItem(ToolMaterials.ADAMANTITE, 1f, -3f)
    val ADAMANTITE_AXE = MyAxeItem(ToolMaterials.ADAMANTITE, 3f, -3f)
    val ADAMANTITE_HOE = MyHoeItem(ToolMaterials.ADAMANTITE, 1, -3f)

    val items = listOf(
        Pair("nuggets/copper", COPPER_NUGGET),

        Pair("ingots/mithril", MITHRIL_INGOT),
        Pair("ingots/raw_mithril", RAW_MITHRIL),
        Pair("nuggets/mithril", MITHRIL_NUGGET),

        Pair("ingots/adamantite", ADAMANTITE_INGOT),
        Pair("ingots/raw_adamantite", RAW_ADAMANTITE),
        Pair("nuggets/adamantite", ADAMANTITE_NUGGET),

        Pair("tools/copper_sword", COPPER_SWORD),
        Pair("tools/copper_pickaxe", COPPER_PICKAXE),
        Pair("tools/copper_shovel", COPPER_SHOVEL),
        Pair("tools/copper_axe", COPPER_AXE),
        Pair("tools/copper_hoe", COPPER_HOE),

        Pair("tools/iron_sword", IRON_SWORD),
        Pair("tools/iron_pickaxe", IRON_PICKAXE),
        Pair("tools/iron_shovel", IRON_SHOVEL),
        Pair("tools/iron_axe", IRON_AXE),
        Pair("tools/iron_hoe", IRON_HOE),

        Pair("tools/mithril_sword", MITHRIL_SWORD),
        Pair("tools/mithril_pickaxe", MITHRIL_PICKAXE),
        Pair("tools/mithril_shovel", MITHRIL_SHOVEL),
        Pair("tools/mithril_axe", MITHRIL_AXE),
        Pair("tools/mithril_hoe", MITHRIL_HOE),

        Pair("tools/adamantite_sword", ADAMANTITE_SWORD),
        Pair("tools/adamantite_pickaxe", ADAMANTITE_PICKAXE),
        Pair("tools/adamantite_shovel", ADAMANTITE_SHOVEL),
        Pair("tools/adamantite_axe", ADAMANTITE_AXE),
        Pair("tools/adamantite_hoe", ADAMANTITE_HOE),
    )

    fun registerAll() {
        for ((path, item) in items) {
            Registry.register(Registry.ITEM, Identifier("modid", path), item)
        }
    }
}
