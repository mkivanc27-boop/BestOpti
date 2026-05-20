package com.mustafa.optibest.mixin;

import net.minecraft.client.render.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin {
    @ModifyArg(method = "build", at = @At(value = "INVOKE", target = "Ljava/nio/ByteBuffer;allocateDirect(I)Ljava/nio/ByteBuffer;"))
    private int shrinkBuffers(int capacity) {
        return capacity / 2; // Buffer boyutlarını yarıya indirerek RAM'i rahatlat
    }
}

