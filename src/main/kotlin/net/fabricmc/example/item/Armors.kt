package net.fabricmc.example.item

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

enum class MyArmorMaterials(
    private val material_name: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    private val repairIngredientSupplier: () -> Ingredient,
) : ArmorMaterial {
    LEATHER("leather", 5, intArrayOf(1, 2, 3, 1), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.LEATHER)) }),
    GOLD("gold", 7, intArrayOf(1, 3, 5, 2), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.GOLD_INGOT)) }),
    CHAIN("chainmail", 15, intArrayOf(1, 4, 5, 2), 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.IRON_INGOT)) }),

    COPPER("copper", 15, intArrayOf(1, 4, 5, 2), 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.IRON_INGOT)) }),
    IRON("iron", 15, intArrayOf(2, 5, 6, 2), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.IRON_INGOT)) }),
    MITHRIL("mithril", 15, intArrayOf(1, 4, 5, 2), 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.IRON_INGOT)) }),
    ADAMANTITE("adamantite", 33, intArrayOf(3, 6, 8, 3), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f,
        { Ingredient.ofItems(*arrayOf<ItemConvertible>(Items.DIAMOND)) });

    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * durabilityMultiplier
    override fun getProtectionAmount(slot: EquipmentSlot): Int = protectionAmounts[slot.entitySlotId]
    override fun getEnchantability(): Int = enchantability
    override fun getEquipSound(): SoundEvent = equipSound
    override fun getRepairIngredient(): Ingredient = repairIngredientSupplier()
    override fun getName(): String = material_name
    override fun getToughness(): Float = toughness
    override fun getKnockbackResistance(): Float = knockbackResistance

    companion object {
        private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    }
}

val COPPER_HELMET = ArmorItem(MyArmorMaterials.COPPER, EquipmentSlot.HEAD, Item.Settings().group(ItemGroup.COMBAT))
val COPPER_CHESTPLATE = ArmorItem(MyArmorMaterials.COPPER, EquipmentSlot.CHEST, Item.Settings().group(ItemGroup.COMBAT))
val COPPER_LEGGINGS = ArmorItem(MyArmorMaterials.COPPER, EquipmentSlot.LEGS, Item.Settings().group(ItemGroup.COMBAT))
val COPPER_BOOTS = ArmorItem(MyArmorMaterials.COPPER, EquipmentSlot.FEET, Item.Settings().group(ItemGroup.COMBAT))

val IRON_HELMET = ArmorItem(MyArmorMaterials.IRON, EquipmentSlot.HEAD, Item.Settings().group(ItemGroup.COMBAT))
val IRON_CHESTPLATE = ArmorItem(MyArmorMaterials.IRON, EquipmentSlot.CHEST, Item.Settings().group(ItemGroup.COMBAT))
val IRON_LEGGINGS = ArmorItem(MyArmorMaterials.IRON, EquipmentSlot.LEGS, Item.Settings().group(ItemGroup.COMBAT))
val IRON_BOOTS = ArmorItem(MyArmorMaterials.IRON, EquipmentSlot.FEET, Item.Settings().group(ItemGroup.COMBAT))

val MITHRIL_HELMET = ArmorItem(MyArmorMaterials.MITHRIL, EquipmentSlot.HEAD, Item.Settings().group(ItemGroup.COMBAT))
val MITHRIL_CHESTPLATE =
    ArmorItem(MyArmorMaterials.MITHRIL, EquipmentSlot.CHEST, Item.Settings().group(ItemGroup.COMBAT))
val MITHRIL_LEGGINGS = ArmorItem(MyArmorMaterials.MITHRIL, EquipmentSlot.LEGS, Item.Settings().group(ItemGroup.COMBAT))
val MITHRIL_BOOTS = ArmorItem(MyArmorMaterials.MITHRIL, EquipmentSlot.FEET, Item.Settings().group(ItemGroup.COMBAT))

val ADAMANTITE_HELMET =
    ArmorItem(MyArmorMaterials.ADAMANTITE, EquipmentSlot.HEAD, Item.Settings().group(ItemGroup.COMBAT))
val ADAMANTITE_CHESTPLATE =
    ArmorItem(MyArmorMaterials.ADAMANTITE, EquipmentSlot.CHEST, Item.Settings().group(ItemGroup.COMBAT))
val ADAMANTITE_LEGGINGS =
    ArmorItem(MyArmorMaterials.ADAMANTITE, EquipmentSlot.LEGS, Item.Settings().group(ItemGroup.COMBAT))
val ADAMANTITE_BOOTS =
    ArmorItem(MyArmorMaterials.ADAMANTITE, EquipmentSlot.FEET, Item.Settings().group(ItemGroup.COMBAT))

val armorItems
    get() = listOf(
        "tools/copper_helmet" to COPPER_HELMET,
        "tools/copper_chestplate" to COPPER_CHESTPLATE,
        "tools/copper_leggings" to COPPER_LEGGINGS,
        "tools/copper_boots" to COPPER_BOOTS,

        "tools/iron_helmet" to IRON_HELMET,
        "tools/iron_chestplate" to IRON_CHESTPLATE,
        "tools/iron_leggings" to IRON_LEGGINGS,
        "tools/iron_boots" to IRON_BOOTS,

        "tools/mithril_helmet" to MITHRIL_HELMET,
        "tools/mithril_chestplate" to MITHRIL_CHESTPLATE,
        "tools/mithril_leggings" to MITHRIL_LEGGINGS,
        "tools/mithril_boots" to MITHRIL_BOOTS,

        "tools/adamantite_helmet" to ADAMANTITE_HELMET,
        "tools/adamantite_chestplate" to ADAMANTITE_CHESTPLATE,
        "tools/adamantite_leggings" to ADAMANTITE_LEGGINGS,
        "tools/adamantite_boots" to ADAMANTITE_BOOTS,
    )