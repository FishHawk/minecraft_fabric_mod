package net.fabricmc.example.generator

import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.example.registry.RegisterItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ModelIds
import net.minecraft.data.client.Models
import net.minecraft.util.Identifier


class ModelGenerator(generator: FabricDataGenerator) : FabricModelProvider(generator) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
        for ((_, block, item) in RegisterBlock.blocks) {
            blockStateModelGenerator.registerSimpleCubeAll(block)
            blockStateModelGenerator.registerParentedItemModel(item, ModelIds.getBlockModelId(block))
        }
    }

    override fun generateItemModels(itemModelGenerator: net.minecraft.data.client.ItemModelGenerator) {
        for ((_, item) in RegisterItem.items) {
            itemModelGenerator.register(item, Models.GENERATED)
        }
    }
}
