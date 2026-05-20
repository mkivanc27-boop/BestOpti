package com.mustafa.optibest.mixin;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRenderMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void optimizeEntityRender(Entity entity, Object camera, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        // Çok uzaktaki varlıkların render'ını iptal ederek FPS'i korur
        if (entity.distanceTo(entity.getOrigin()) > 64) {
            cir.setReturnValue(false);
        }
    }
}

