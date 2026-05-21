package com.mustafa.optibest.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    private int worldTickCount = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void trackWorldTick(
            java.util.function.BooleanSupplier shouldKeepTicking,
            CallbackInfo ci) {
        worldTickCount++;
    }
}
