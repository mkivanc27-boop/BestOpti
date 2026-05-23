package com.mustafa.optibest.mixin;

import com.mustafa.optibest.BestOptiConfig;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleOptimizeMixin {

    private int particleCount = 0;

    @Inject(
        method = "addParticle(Lnet/minecraft/client/particle/Particle;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void limitParticles(Particle particle, CallbackInfo ci) {
        BestOptiConfig config = BestOptiConfig.get();
        if (!config.limitParticles) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        particleCount++;
        if (particleCount > config.maxParticles) {
            particleCount = 0;
        }

        if (particleCount > config.maxParticles * 0.8) {
            ci.cancel();
            return;
        }

        double distanceSq = particle.getBoundingBox()
            .getCenter()
            .squaredDistanceTo(
                client.player.getX(),
                client.player.getY(),
                client.player.getZ()
            );

        if (distanceSq > 24 * 24) {
            ci.cancel();
        }
    }
}
