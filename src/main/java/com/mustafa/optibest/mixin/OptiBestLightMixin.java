package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.chunk.light.LightingProvider;

@Mixin(LightingProvider.class) // Kendi hedef sınıfın neyse onunla değiştir
public class OptiBestLightMixin {

    @Inject(method = "doLightUpdates", at = @At("HEAD"), cancellable = true)
    private void skipLaggyLightUpdates(CallbackInfo ci) {
        // Işık güncelleme optimizasyon kodların burada olacak
    }
}
