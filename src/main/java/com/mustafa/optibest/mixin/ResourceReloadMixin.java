package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftClient.class)
public class ResourceReloadMixin {

    @Inject(
        method = "reloadResources(Z)Ljava/util/concurrent/CompletableFuture;",
        at = @At("HEAD")
    )
    private void beforeReload(boolean force,
            CallbackInfoReturnable<CompletableFuture<?>> cir) {
        System.gc();
    }
}
