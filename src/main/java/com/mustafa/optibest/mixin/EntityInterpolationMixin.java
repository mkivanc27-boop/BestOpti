package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityInterpolationMixin {

    @Inject(method = "updateTrackedPositionAndAngles", at = @At("HEAD"), cancellable = true)
    private void onUpdateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int smoothSteps, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        
        // Only update the position with the Vec3d object. No need for the lerp steps setter here.
        self.updateTrackedPositionAndAngles(new Vec3d(x, y, z), yaw, pitch);
        
        ci.cancel();
    }
}
