package net.fabricmc.example

import net.fabricmc.api.ModInitializer
import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.example.registry.RegisterItem
import org.slf4j.LoggerFactory


// FabricLoader.getInstance().configDir.resolve("modid.json")
class ExampleMod : ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    companion object {
        @JvmField
        val LOGGER = LoggerFactory.getLogger("modid")
    }

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Hello Fabric world!");
        RegisterBlock.registerAll()
        RegisterItem.registerAll()
    }
}
