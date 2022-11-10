package net.fabricmc.example.mixin;

import net.fabricmc.example.util.Helpers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "canHarvest", at = @At("TAIL"), cancellable = true)
    private void modifyCanHarvest(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        final boolean oldValue = cir.getReturnValue();
        final boolean newValue = PlayerEntityKt.modifyCanHarvestKt(Helpers.cast(this), state);
        if (oldValue != newValue) {
            cir.setReturnValue(newValue);
        }
    }

    @Inject(method = "getBlockBreakingSpeed", at = @At("TAIL"), cancellable = true)
    private void modifyBreakSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        final float oldSpeed = cir.getReturnValue();
//        cir.setReturnValue(0f);
//        final float newSpeed = EventHandler.modifyBreakSpeed(Helpers.cast(this), state, null, oldSpeed);
//        if (oldSpeed != newSpeed) {
//            cir.setReturnValue(newSpeed);
//        }
    }
}