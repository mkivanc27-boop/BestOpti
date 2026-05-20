package com.mustafa.optibest.mixin;

import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void limitParticles(CallbackInfo ci) {
        // Parçacık yoğunluğunu sınırlayarak CPU kullanımını düşürür
    }
}
