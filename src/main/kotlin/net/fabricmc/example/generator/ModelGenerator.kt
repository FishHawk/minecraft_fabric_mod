package net.fabricmc.example.generator

import net.fabricmc.example.minecraftIdentifier
import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.example.registry.RegisterItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.AnvilBlock
import net.minecraft.block.Block
import net.minecraft.data.client.*
import net.minecraft.util.Identifier
import java.util.*

private val BODY = TextureKey.of("body", TextureKey.END)

private val TEMPLATE_ANVIL = TexturedModel.makeFactory(
    {
        val identifier = TextureMap.getId(it)
        val uniformPath = identifier.path
            .removeSuffix("_damaged_0")
            .removeSuffix("_damaged_1")
            .removeSuffix("_damaged_2")
        val bodyIdentifier = Identifier(identifier.namespace, "${uniformPath}_body")

        TextureMap()
            .put(TextureKey.TOP, TextureMap.getSubId(it, "_top"))
            .put(BODY, bodyIdentifier)
    },
    Model(
        Optional.of(minecraftIdentifier("block/template_anvil")),
        Optional.empty(),
        TextureKey.TOP, BODY,
    ),
)

private fun BlockStateModelGenerator.registerAnvilWithBody(anvil: Block) {
    val identifier = TEMPLATE_ANVIL.upload(anvil, this.modelCollector)
    this.blockStateCollector.accept(
        BlockStateModelGenerator.createSingletonBlockState(anvil, identifier)
            .coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates())
    )
}

class ModelGenerator(generator: FabricDataGenerator) : FabricModelProvider(generator) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
        for ((_, block, item) in RegisterBlock.blocks) {
            if (block is AnvilBlock) {
                blockStateModelGenerator.registerAnvilWithBody(block)
                blockStateModelGenerator.registerParentedItemModel(item, ModelIds.getBlockModelId(block))
            } else {
                blockStateModelGenerator.registerSimpleCubeAll(block)
                blockStateModelGenerator.registerParentedItemModel(item, ModelIds.getBlockModelId(block))
            }
        }
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        for ((_, item) in RegisterItem.items) {
            itemModelGenerator.register(item, Models.GENERATED)
        }
    }
}
