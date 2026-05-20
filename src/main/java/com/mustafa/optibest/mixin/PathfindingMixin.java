package com.mustafa.optibest.mixin;

import net.minecraft.entity.ai.pathing.EntityNavigation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityNavigation.class)
public class PathfindingMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void reduceAITick(CallbackInfo ci) {
        // Yaratıklar yolu her tickte hesaplamasın, 5 tickte bir hesaplasın.
        if (Math.random() > 0.2) ci.cancel();
    }
}

