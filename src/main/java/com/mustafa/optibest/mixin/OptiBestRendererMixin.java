package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import org.joml.Matrix4f;

@Mixin(WorldRenderer.class)
public class OptiBestRendererMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void simplifyRender(
        net.minecraft.client.render.RenderTickCounter tickCounter, 
        boolean renderWorldOutline, 
        Camera camera, 
        GameRenderer gameRenderer, 
        LightmapTextureManager lightmapManager, 
        Matrix4f projectionMatrix, 
        Matrix4f viewMatrix, 
        CallbackInfo ci
    ) {
        // Render optimizasyonlarını buraya yazabilirsin.
        // Artık oyun metodun imzasını (parametrelerini) tanıdığı için crash vermeyecek.
    }
}
