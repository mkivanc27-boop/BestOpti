package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityLogicMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void optiBest_reduceEntityTick(CallbackInfo ci) {
        // Hedef: Entity (yaratık/oyuncu/nesne) tick hesaplamalarını seyrekleştirerek 
        // CPU yükünü hafifletmek. 
        // age % 2 demek: Her 2 tick'te bir çalış demek. CPU'yu neredeyse yarı yarıya rahatlatır.
        if (((Entity)(Object)this).age % 2 != 0) {
            ci.cancel();
        }
    }
}
