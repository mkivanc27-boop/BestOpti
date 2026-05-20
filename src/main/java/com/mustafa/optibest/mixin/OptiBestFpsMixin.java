package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class) // Kendi hedef sınıfın neyse onunla değiştir
public class OptiBestFpsMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void fastTick(CallbackInfo ci) {
        // FPS optimizasyon kodların burada olacak
    }
}
