package com.mustafa.optibest.mixin;

import net.minecraft.client.render.WeatherRendering;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.render.WorldRenderer")
public class WeatherThrottleMixin {
    private int weatherTick = 0;

    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
    private void throttleWeather(net.minecraft.client.util.math.MatrixStack matrices, float tickDelta, double x, double y, double z, CallbackInfo ci) {
        weatherTick++;
        MinecraftClient client = MinecraftClient.getInstance();
        int fps = client != null ? client.getCurrentFps() : 60;
        if (fps < 40 && weatherTick % 2 != 0) ci.cancel();
    }
}
