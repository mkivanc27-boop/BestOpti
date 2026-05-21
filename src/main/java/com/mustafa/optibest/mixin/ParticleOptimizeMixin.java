package com.mustafa.optibest.mixin;

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
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        particleCount++;

        // Her 100 parçacıkta bir sıfırla
        if (particleCount > 100) particleCount = 0;

        // 100'den fazla aktif parçacık varsa yenisini ekleme
        if (particleCount > 80) {
            ci.cancel();
            return;
        }

        // Uzaktaki parçacıkları atla
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
