package com.mustafa.optibest.mixin;

import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTickCounter.Dynamic.class)
public class RenderTickCounterMixin {

    @Inject(method = "beginRenderTick(J)I", at = @At("HEAD"), cancellable = true)
    private void capRenderTicks(long timeMillis, CallbackInfoReturnable<Integer> cir) {
        // Tek frame'de çok fazla tick birikimini engelle
        // Lag spike'larında oyunun "zıplamasını" önler
        int ticks = cir.getReturnValue();
        if (ticks != null && ticks > 8) {
            cir.setReturnValue(8);
        }
    }
}
