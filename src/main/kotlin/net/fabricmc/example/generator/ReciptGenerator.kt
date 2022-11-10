package net.fabricmc.example.generator

import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.example.registry.RegisterItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.RecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import java.util.function.Consumer

private data class OreBundle(
    val stoneVariant: Item,
    val deepslateVariant: Item,
    val block: Item,
    val rawBlock: Item,
    val raw: Item,
    val ingot: Item,
    val nugget: Item,
) {
    val meltable
        get() = listOf(stoneVariant, deepslateVariant, raw)
}

private val oreBundles = listOf(
    OreBundle(
        stoneVariant = RegisterBlock.MITHRIL_BLOCK_ITEM,
        deepslateVariant = RegisterBlock.DEEPSLATE_MITHRIL_ORE_ITEM,
        block = RegisterBlock.MITHRIL_BLOCK_ITEM,
        rawBlock = RegisterBlock.RAW_MITHRIL_BLOCK_ITEM,
        raw = RegisterItem.RAW_MITHRIL,
        ingot = RegisterItem.MITHRIL_INGOT,
        nugget = RegisterItem.MITHRIL_NUGGET,
    )
)

private fun offerRecipesBetweenIngotAndNugget(
    exporter: Consumer<RecipeJsonProvider>,
    nugget: ItemConvertible,
    ingot: ItemConvertible
) {
    RecipeProvider.offerReversibleCompactingRecipesWithCompactingRecipeGroup(
        exporter, nugget, ingot,
        RecipeProvider.getRecipeName(ingot) + "_from_nugget",
        null,
    )
}

private fun offerOreRecipes(exporter: Consumer<RecipeJsonProvider>, bundle: OreBundle) {
    offerRecipesBetweenIngotAndNugget(exporter, bundle.nugget, bundle.ingot)
    RecipeProvider.offerReversibleCompactingRecipes(exporter, bundle.ingot, bundle.block)
    RecipeProvider.offerReversibleCompactingRecipes(exporter, bundle.raw, bundle.rawBlock)
    RecipeProvider.offerSmelting(exporter, bundle.meltable, bundle.ingot, 0.45F, 300, "test")
}

class RecipeGenerator(generator: FabricDataGenerator) : FabricRecipeProvider(generator) {
    override fun generateRecipes(exporter: Consumer<RecipeJsonProvider>) {
        offerRecipesBetweenIngotAndNugget(exporter, RegisterItem.COPPER_NUGGET, Items.COPPER_INGOT)
        for (bundle in oreBundles) {
            offerOreRecipes(exporter, bundle)
        }
    }
}
