package com.mustafa.optibest.mixin;

import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(RenderTickCounter.class)
public class RenderTickCounterMixin {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(FF)F"), index = 1)
    private float stabilizeRender(float value) {
        return 0.1f; // Render'ın işlemciyi boğmasını engellemek için sabitleme
    }
}

