package com.mustafa.optibest.mixin;

import net.minecraft.client.sound.SoundSystem;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundSystem.class)
public class SoundOptimizationMixin {

    private int soundTickCounter = 0;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void throttleSoundTick(CallbackInfo ci) {
        soundTickCounter++;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        int fps = client.getCurrentFps();

        // FPS çok düşükse ses tick'ini seyrekleştir
        // Ses biraz gecikmeli olabilir ama oyun akıcı kalır
        if (fps < 25 && soundTickCounter % 2 != 0) {
            ci.cancel();
        }
    }
}
