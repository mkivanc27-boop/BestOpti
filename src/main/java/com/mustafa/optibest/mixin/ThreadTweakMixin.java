package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ThreadTweakMixin {
    @Inject(method = "run", at = @At("HEAD"))
    private void optimizeThreadPriority(CallbackInfo ci) {
        // Render thread'ini yüksek önceliğe al, arka plan işlerini beklet
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    }
}

