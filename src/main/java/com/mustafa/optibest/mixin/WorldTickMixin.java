package com.mustafa.optibest.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.BooleanSupplier;

@Mixin(ClientWorld.class)
public class WorldTickMixin {

    private int tickCounter = 0;

    @Inject(method = "tickEntities", at = @At("HEAD"), cancellable = true)
    private void throttleEntityTicking(CallbackInfo ci) {
        tickCounter++;
        if (tickCounter % 2 != 0) {
            ci.cancel();
        }
    }
}
