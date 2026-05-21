package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityInterpolationMixin {

    @Inject(method = "updateTrackedPositionAndAngles", at = @At("HEAD"), cancellable = true)
    private void onUpdateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int smoothSteps, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        
        // 1. Pass coordinates using the required Vec3d object instead of raw doubles
        self.updateTrackedPositionAndAngles(new Vec3d(x, y, z), yaw, pitch);
        
        // 2. Set the interpolation steps separately 
        // Note: depending on your Minecraft version, this may be setLerpSteps() or setClientLerpSteps()
        self.setLerpSteps(smoothSteps); 
        
        ci.cancel();
    }
}
