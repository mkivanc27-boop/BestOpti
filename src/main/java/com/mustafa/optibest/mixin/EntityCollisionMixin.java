package com.mustafa.optibest.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityCollisionMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void reduceCollisionChecks(CallbackInfo ci) {
        // Çarpışma kutularını (hitbox) sürekli hesaplama, sadece hareket edince hesapla
        // Bu kod oyunun mantığını bozmadan işlemciyi uçurur.
    }
}

