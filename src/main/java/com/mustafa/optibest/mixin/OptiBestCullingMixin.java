package com.mustafa.optibest.mixin;

import net.minecraft.client.render.Frustum;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Frustum.class)
public class OptiBestCullingMixin {

    @Inject(method = "isVisible", at = @At("HEAD"), cancellable = true)
    private void fastCullCheck(Box box, CallbackInfoReturnable<Boolean> cir) {
        if (box == null) {
            cir.setReturnValue(false);
            return;
        }

        // 1.21'de Box field'ları doğrudan erişilir, getter yok
        double xLen = box.maxX - box.minX;
        double yLen = box.maxY - box.minY;
        double zLen = box.maxZ - box.minZ;

        // Negatif veya sıfır boyutlu box
        if (xLen <= 0 || yLen <= 0 || zLen <= 0) {
            cir.setReturnValue(false);
            return;
        }

        // Çok küçük box'ları atla
        if (xLen * yLen * zLen < 0.0001) {
            cir.setReturnValue(false);
        }
    }
}
