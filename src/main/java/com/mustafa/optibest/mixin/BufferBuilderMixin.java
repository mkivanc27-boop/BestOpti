package com.mustafa.optibest.mixin;

import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin {

    @Inject(method = "begin", at = @At("HEAD"))
    private void optimizeBegin(
            net.minecraft.client.render.DrawMode drawMode,
            VertexFormat format,
            CallbackInfoReturnable<?> cir) {
        // begin() her render frame'de çağrılır
        // Hook olarak burada durmak ileride
        // gereksiz buffer allocationlarını önlemek için kullanılabilir
    }
}
