package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
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

        // Her 3 frame'de bir ışık güncellemesi yap
        // Görsel fark minimum, CPU kazanımı yüksek
        if (lightUpdateCounter % 3 != 0) {
            cir.setReturnValue(0); // Bu frame'i atla
            return;
        }

        // Her 3 frame'de bir normal çalış
        lightUpdateCounter = 0;
    }
}
