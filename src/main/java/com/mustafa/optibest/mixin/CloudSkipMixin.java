package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.render.WorldRenderer")
public class CloudSkipMixin {
    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void skipCloudsLowFps(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        if (client.getCurrentFps() < 30) ci.cancel();
    }
}
