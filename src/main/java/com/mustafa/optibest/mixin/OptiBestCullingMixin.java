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

        // Dejenere box kontrolü (sıfır boyutlu veya negatif)
        if (box.getXLength() < 0 || box.getYLength() < 0 || box.getZLength() < 0) {
            cir.setReturnValue(false);
            return;
        }

        // Çok küçük box'ları (0.01 bloktan küçük) atla — görünmez zaten
        double volume = box.getXLength() * box.getYLength() * box.getZLength();
        if (volume < 0.0001) {
            cir.setReturnValue(false);
        }
    }
}
