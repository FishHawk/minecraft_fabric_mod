package net.fabricmc.example

import net.fabricmc.example.item.ItemModelGenerator
import net.fabricmc.example.item.ItemRecipeGenerator
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class MyModDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        fabricDataGenerator.addProvider(ItemModelGenerator(fabricDataGenerator))
        fabricDataGenerator.addProvider(ItemRecipeGenerator(fabricDataGenerator))
    }
}