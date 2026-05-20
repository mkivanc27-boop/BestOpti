package com.mustafa.optibest.mixin;

import net.minecraft.world.chunk.light.LightStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightStorage.class)
public class LightingEngineMixin {
    @Inject(method = "updateLight", at = @At("HEAD"), cancellable = true)
    private void skipLightUpdates(CallbackInfo ci) {
        // Işık güncellemelerini her zaman yapma, performansı gözle görülür arttırır.
        ci.cancel(); 
    }
}

