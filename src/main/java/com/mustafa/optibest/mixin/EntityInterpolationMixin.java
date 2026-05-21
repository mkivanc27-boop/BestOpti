package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityInterpolationMixin {

    @Inject(method = "updateTrackedPositionAndAngles", at = @At("HEAD"), cancellable = true)
    private void smoothInterpolation(
            double x, double y, double z,
            float yaw, float pitch,
            int interpolationSteps,
            CallbackInfo ci) {

        Entity self = (Entity)(Object)this;

        // Interpolation adımını artır — pozisyon geçişi daha yumuşak olur
        int smoothSteps = Math.max(interpolationSteps, 5);

        self.updateTrackedPositionAndAngles(x, y, z, yaw, pitch, smoothSteps);
        ci.cancel();
    }
          }
