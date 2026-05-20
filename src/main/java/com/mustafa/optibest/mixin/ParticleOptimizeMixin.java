package com.mustafa.optibest.mixin;

import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleOptimizeMixin {
    // Metodu değiştirdik, @ModifyArg yerine @Inject ile daha güvenli bir noktaya (HEAD) ekliyoruz.
    @Inject(method = "addParticle(Lnet/minecraft/client/particle/Particle;)V", at = @At("HEAD"), cancellable = true)
    private void limitParticles(Particle particle, CallbackInfo ci) {
        // Kodun çalışması için burayı şimdilik boş bırak, hata devam ederse burayı dolduracağız.
    }
}
