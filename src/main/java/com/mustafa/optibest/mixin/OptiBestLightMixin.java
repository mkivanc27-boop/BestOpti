package com.mustafa.optibest.mixin;

import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.callback.CallbackInfo;

@Mixin(LightingProvider.class)
public class OptiBestLightMixin {
    @Inject(method = "doLightUpdates", at = @At("HEAD"), cancellable = true)
    private void skipLaggyLightUpdates(CallbackInfo ci) {
        // Işık güncellemelerini seyrelt
    }
}

