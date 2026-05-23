package com.mustafa.optibest.mixin;

import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightingProvider.class)
public class LightingEngineMixin {

    private int lightUpdateCounter = 0;

    @Inject(method = "doLightUpdates", at = @At("HEAD"), cancellable = true)
    private void throttleLightUpdates(CallbackInfoReturnable<Integer> cir) {
        lightUpdateCounter++;

        // Sadece çok yüksek pending update varsa throttle yap
        // Normal oyunda (blok kırma, patlama) hiç atlanmaz
        int pending = cir.getReturnValue() != null ? cir.getReturnValue() : 0;

        // 500'den az pending update varsa → her zaman çalış
        if (pending < 500) return;

        // 500+ pending varsa → her 2 frame'de bir atla (spam durumu)
        if (lightUpdateCounter % 2 != 0) {
            cir.setReturnValue(0);
        }
    }
}
