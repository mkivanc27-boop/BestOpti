package com.mustafa.optibest.mixin;

import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import java.util.Map;
import java.util.Queue;

@Mixin(ParticleManager.class)
public class ParticleHardCapMixin {
    @Shadow private static final int MAX_PARTICLES_PER_LAYER = 16384;

    @Inject(method = "addParticle(Lnet/minecraft/client/particle/Particle;)V", at = @At("HEAD"), cancellable = true)
    private void capParticles(net.minecraft.client.particle.Particle particle, CallbackInfo ci) {
        ParticleManager self = (ParticleManager)(Object)this;
        if (((IParticleManager) self).getParticleCount() > 200) {
            ci.cancel();
        }
    }
}
