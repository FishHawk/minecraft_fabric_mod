package net.fabricmc.example.block

import net.fabricmc.example.registry.RegisterBlock
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.AnvilBlock
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.screen.AnvilScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.registry.Registry
import net.minecraft.world.World


class CustomAnvilScreenHandler(
    var customAnvil: CustomAnvil,
    syncId: Int,
    inventory: PlayerInventory?,
    context: ScreenHandlerContext?
) : AnvilScreenHandler(syncId, inventory, context) {
    init {
        this.context.run { world: World, blockPos: BlockPos? ->
            if (world.getBlockState(blockPos).isIn(FATags.FABRICANVILS)) {
                xpLimit = customAnvil.getXPLimit()
            }
        }
    }

    override fun updateResult() {
        context.run { world: World, blockPos: BlockPos? ->
            if (!world.isClient) {
                val buf = PacketByteBufs.create()
                buf.writeInt(xpLimit)
                ServerPlayNetworking.send(player as ServerPlayerEntity, FAUtils.FAIdentifier("anvilxplimit"), buf)
            } else {
                FabricAnvilsClient.xpLimitOnClient = false
            }
        }
        super.updateResult()
    }

    override fun onTakeOutput(player: PlayerEntity, stack: ItemStack) {
        super.onTakeOutput(player, stack)
        context.run { world: World, pos: BlockPos? ->
            val blockState = world.getBlockState(pos)
            if (!player.abilities.creativeMode && blockState.isIn(FATags.FABRICANVILS) && player.random
                    .nextFloat() < customAnvil.getDamagingChance()
            ) {
                val blockState2: BlockState = customAnvil.getStateOnLanding(blockState)
                if (blockState2 == null) {
                    world.removeBlock(pos, false)
                    world.playSound(null, pos, customAnvil.getBreakSound(), SoundCategory.BLOCKS, 10f, 1f)
                } else {
                    world.setBlockState(pos, blockState2, 2)
                    world.playSound(null, pos, customAnvil.getForgeSound(), SoundCategory.BLOCKS, 10f, 1f)
                }
            } else {
                world.playSound(null, pos, customAnvil.getForgeSound(), SoundCategory.BLOCKS, 10f, 1f)
            }
        }
    }

    companion object {
        var xpLimit = 0
    }
}


class CopperAnvil(settings: Settings) : AnvilBlock(settings) {
    fun getLandingStateLocal(fallingState: BlockState): BlockState? {
        return if (fallingState.isOf(RegisterBlock.COPPER_ANVIL_DAMAGED_0)) {
            RegisterBlock.COPPER_ANVIL_DAMAGED_1.defaultState
                .with(FACING, fallingState.get(FACING) as Direction)
        } else if (fallingState.isOf(RegisterBlock.COPPER_ANVIL_DAMAGED_1)) {
            RegisterBlock.COPPER_ANVIL_DAMAGED_2.defaultState
                .with(FACING, fallingState.get(FACING) as Direction)
        } else {
            null
        }
    }
}


val DEMO_BLOCK_ENTITY: BlockEntityType<CustomAnvilBlockEntity> = Registry.register(
    Registry.BLOCK_ENTITY_TYPE,
    Identifier("modid", "demo_block_entity"),
    FabricBlockEntityTypeBuilder.create(
        { pos: BlockPos, state: BlockState -> CustomAnvilBlockEntity(pos, state) },
        RegisterBlock.COPPER_ANVIL_DAMAGED_0
    ).build()
)

class CustomAnvilBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(DEMO_BLOCK_ENTITY, pos, state) {

    var durability = 7
    var maxDurability = 100

    override fun writeNbt(nbt: NbtCompound) {
        nbt.putInt("durability", durability)
        nbt.putInt("max_durability", maxDurability)
        super.writeNbt(nbt)

    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        maxDurability = nbt.getInt("max_durability")
        durability = nbt.getInt("durability")
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener>? {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }
}

class CustomAnvil(settings: Settings) : AnvilBlock(settings), BlockEntityProvider {
//    abstract fun getLandingStateLocal(fallingState: BlockState): BlockState?

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return CustomAnvilBlockEntity(pos, state)
    }
}
