package com.mustafa.optibest.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.NetworkSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class NetworkPacketMixin {

    private int packetCounter = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void optimizeNetworkTick(CallbackInfo ci) {
        packetCounter++;
        // Network tick'e asla dokunma
        // Sadece sayacı tut, başka Mixin'ler kullanabilir
    }
}
