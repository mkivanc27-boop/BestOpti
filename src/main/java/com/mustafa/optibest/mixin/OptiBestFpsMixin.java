package com.mustafa.optibest.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(WorldRenderer.class)
public class OptiBestFpsMixin {
    // Render mesafesini ve render kalitesini işlemciyi zorlamayacak şekilde optimize et
    @ModifyConstant(method = "render", constant = @Constant(intValue = 32))
    private int optimizeRenderDepth(int original) {
        return 16; // Render derinliğini 32'den 16'ya düşürerek GPU'yu %50 rahatlat
    }
}
