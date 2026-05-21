package com.mustafa.optibest.mixin;

import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureManager.class)
public class TextureLoadingMixin {

    @Inject(method = "registerTexture", at = @At("HEAD"))
    private void optimizeTextureRegister(
            Identifier id, AbstractTexture texture, CallbackInfo ci) {
        // registerTexture her texture yüklendiğinde çağrılır
        // Texture kayıt sürecini izle
        // Duplicate texture kayıtlarını burada önleyebiliriz
    }
}
