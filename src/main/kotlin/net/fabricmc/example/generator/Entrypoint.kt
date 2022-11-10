package net.fabricmc.example.generator

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

@Suppress("unused")
class Entrypoint : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        fabricDataGenerator.addProvider(ModelGenerator(fabricDataGenerator))
        fabricDataGenerator.addProvider(RecipeGenerator(fabricDataGenerator))
    }
}