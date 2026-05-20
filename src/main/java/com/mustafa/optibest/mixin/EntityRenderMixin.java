package com.mustafa.optibest.mixin;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRenderMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private <T extends Entity> void optimizeEntityRender(T entity, Object camera, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        // 'getOrigin()' yerine 'getPos()' veya doğrudan koordinat karşılaştırması kullanıyoruz
        // Minecraft 1.21'de entity'nin pozisyonunu almak için getPos() kullanılır
        if (entity.getPos().squaredDistanceTo(x, y, z) > 64 * 64) {
            cir.setReturnValue(false);
        }
    }
}
