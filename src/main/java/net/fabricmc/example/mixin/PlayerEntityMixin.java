package net.fabricmc.example.mixin;

import net.fabricmc.example.util.Helpers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "canHarvest", at = @At("TAIL"), cancellable = true)
    private void modifyCanHarvest(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        final boolean oldValue = cir.getReturnValue();

        if (state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.PLANKS)) {
            final PlayerEntity player = Helpers.cast(this);
            final boolean newValue = player.getMainHandStack().isSuitableFor(state);
            if (oldValue != newValue) {
                cir.setReturnValue(newValue);
            }
        }
    }

//    @Inject(method = "getBlockBreakingSpeed", at = @At("TAIL"), cancellable = true)
//    private void modifyBreakSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
//        final float oldSpeed = cir.getReturnValue();
//        cir.setReturnValue(0f);
//        final float newSpeed = EventHandler.modifyBreakSpeed(Helpers.cast(this), state, null, oldSpeed);
//        if (oldSpeed != newSpeed) {
//            cir.setReturnValue(newSpeed);
//        }
//    }
}