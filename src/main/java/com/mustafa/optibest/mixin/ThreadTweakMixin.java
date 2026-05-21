package com.mustafa.optibest.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ThreadTweakMixin {

    @Inject(method = "run", at = @At("HEAD"))
    private void optimizeThreadPriority(CallbackInfo ci) {
        Thread renderThread = Thread.currentThread();
        renderThread.setPriority(Thread.MAX_PRIORITY - 1);
        renderThread.setName("BestOpti-RenderThread");
        // setDaemon kaldırıldı — başlamış thread'de çalışmaz
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void optimizeWorkerThreads(CallbackInfo ci) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
            String.valueOf(Math.max(2,
                Runtime.getRuntime().availableProcessors() - 1))
        );
    }
}
