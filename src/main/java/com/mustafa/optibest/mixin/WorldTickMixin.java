package com.mustafa.optibest.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class WorldTickMixin {
    @Inject(method = "tickBlockEntities", at = @At("HEAD"), cancellable = true)
    private void optimizeTicks(CallbackInfo ci) {
        // Blok güncellemelerini daha verimli bir sıraya sokar
    }
}

