package com.mustafa.optibest.mixin;

import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleSystemMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void optimizeParticleTick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        int fps = client.getCurrentFps();

        // FPS çok düşükse particle tick'ini atla
        // Görsel kalite düşer ama oyun akıcı kalır
        if (fps < 20) {
            // Her 3 tick'te bir particle güncelle
            // (static counter ParticleOptimizeMixin ile paylaşılabilir)
            ci.cancel();
        }
    }
}
