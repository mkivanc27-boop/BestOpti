package com.mustafa.optibest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;

@Mixin(EntityRenderer.class) // Kendi hedef sınıfın neyse onunla değiştirebilirsin
public class OptiBestEntityRendererMixin {

    // Kendi render metodunun parametreleri farklıysa burayı eskisi gibi yapabilirsin
    // ÖNEMLİ OLAN: Yukarıdaki CallbackInfo import'unun doğru kalması!
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void optimizeEntityRender(Entity entity, float yaw, float tickDelta, net.minecraft.client.util.math.MatrixStack matrices, net.minecraft.client.render.VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        // Entity culling ve render optimizasyon kodların burada olacak
    }
}
