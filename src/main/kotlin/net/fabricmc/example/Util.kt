package net.fabricmc.example

import net.minecraft.util.Identifier

fun identifier(path: String) = Identifier("modid", path)

fun minecraftIdentifier(path: String) = Identifier(Identifier.DEFAULT_NAMESPACE, path)