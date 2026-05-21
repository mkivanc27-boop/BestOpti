package com.mustafa.optibest.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.BooleanSupplier;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onWorldTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        // Dünya tick sayacı — diğer optimizasyonlar için kullanılabilir
    }
}
