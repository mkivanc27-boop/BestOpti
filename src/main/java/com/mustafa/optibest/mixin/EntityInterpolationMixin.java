package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityInterpolationMixin {

    // The method signature must exactly match the new 1.21 parameters: Vec3d, float, float.
    @Inject(method = "updateTrackedPositionAndAngles", at = @At("HEAD"), cancellable = true)
    private void onUpdateTrackedPositionAndAngles(Vec3d pos, float yaw, float pitch, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        
        // Pass the Vec3d object and floats directly into the method
        self.updateTrackedPositionAndAngles(pos, yaw, pitch);
        
        ci.cancel();
    }
}
