package com.mustafa.optibest.mixin;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class ChunkBatchMixin {

    @Inject(method = "scheduleTerrainUpdate", at = @At("HEAD"))
    private void onTerrainUpdate(CallbackInfo ci) {
        // Terrain update hook
    }
}
