package com.mustafa.optibest.mixin;

import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleSystemMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void limitParticleUpdates(CallbackInfo ci) {
        // Parçacık hesaplamasını seyrelt
        if (Math.random() > 0.5) ci.cancel(); 
    }
}

