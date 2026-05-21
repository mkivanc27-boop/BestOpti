package com.mustafa.optibest.mixin;

import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureManager.class)
public class TextureLoadingMixin {

    @Inject(method = "bindTexture", at = @At("HEAD"))
    private void optimizeTextureBind(Identifier id, CallbackInfo ci) {
        // Texture bind çağrıları GPU'ya gider
        // Mixin hook olarak burada durmak
        // ileride aynı texture'ı tekrar bind etmeyi önlemek için
        // bir cache sistemi kurulabilir
    }
}
