package net.fabricmc.example.item

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.function.Consumer


data class OreBundle(
    val stoneVariant: Item,
    val deepslateVariant: Item,
    val block: Item,
    val rawBlock: Item,
    val raw: Item,
    val ingot: Item,
    val nugget: Item,
)

val oreBundles = listOf(
    OreBundle(
        stoneVariant = MITHRIL.MITHRIL_ORE_ITEM,
        deepslateVariant = MITHRIL.DEEPSLATE_MITHRIL_ORE_ITEM,
        block = MITHRIL.MITHRIL_BLOCK_ITEM,
        rawBlock = MITHRIL.RAW_MITHRIL_BLOCK_ITEM,
        raw = RAW_MITHRIL,
        ingot = MITHRIL_INGOT,
        nugget = MITHRIL_NUGGET,
    )
)

object Test {
    fun registerItems() {
//        for ((path, block, item) in oreBlocks) {
//            Registry.register(Registry.BLOCK, Identifier("modid", path), block)
//            Registry.register(Registry.ITEM, Identifier("modid", path), item)
//        }
        for ((path, item) in toolItems) {
            Registry.register(Registry.ITEM, Identifier("modid", path), item)
        }
    }
}

class ItemModelGenerator(generator: FabricDataGenerator) : FabricModelProvider(generator) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {}
    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        for ((_, item) in oreItems + toolItems + armorItems) {
            itemModelGenerator.register(item, Models.GENERATED)
        }
    }
}

private fun generate9to1Recipes(exporter: Consumer<RecipeJsonProvider>, item9: Item, item1: Item) {
    ShapelessRecipeJsonBuilder
        .create(item9, 9)
        .input(item1)
        .offerTo(exporter)
    ShapedRecipeJsonBuilder
        .create(item1)
        .pattern("XXX").pattern("XXX").pattern("XXX")
        .input('X', item9)
        .offerTo(exporter)
}

class ItemRecipeGenerator(generator: FabricDataGenerator) : FabricRecipeProvider(generator) {
    override fun generateRecipes(exporter: Consumer<RecipeJsonProvider>) {
        generate9to1Recipes(exporter, COPPER_NUGGET, Items.COPPER_INGOT)
        for (bundle in oreBundles) {
            generate9to1Recipes(exporter, bundle.nugget, bundle.ingot)
            generate9to1Recipes(exporter, bundle.ingot, bundle.block)
            generate9to1Recipes(exporter, bundle.raw, bundle.rawBlock)
            CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(
                    bundle.stoneVariant,
                    bundle.deepslateVariant,
                    bundle.raw,
                ),
                bundle.ingot,
                0.45F,
                300,
            )
        }
    }
}
