package com.mustafa.optibest.mixin;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    private int worldTickCount = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void trackWorldTick(CallbackInfo ci) {
        worldTickCount++;
    }

    // Uzak block event'lerini throttle et
    @Inject(method = "addBlockEntityTicker", at = @At("HEAD"), cancellable = true)
    private void limitBlockEntityTickers(CallbackInfo ci) {
        // Her tick'te yeni ticker ekleme — 2 tick'te bir kontrol et
        if (worldTickCount % 2 != 0) {
            ci.cancel();
        }
    }
} 
