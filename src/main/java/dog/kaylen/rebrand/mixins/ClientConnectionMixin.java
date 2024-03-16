/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand.mixins;

import dog.kaylen.rebrand.RebrandClientMod;
import dog.kaylen.rebrand.config.RebrandModConfig;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Prevents the client from sending custom payload packets ( except vanilla ones that aren't (un)register ) when ghost mode is enabled.
 */
@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
	@Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;Z)V", at = @At("HEAD"), cancellable = true)
	public void rebrand$send(Packet<?> packet, @Nullable PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
		// default to ghost mode if the mod is not initialized - shouldn't occur!
		if (RebrandClientMod.getInstance() == null) {
			ci.cancel();
		}
		RebrandModConfig config = RebrandClientMod.getInstance().getConfig();
		if (!config.enable || !config.ghostMode) {
			return;
		}
		if (!(packet instanceof CustomPayloadC2SPacket)) {
			return;
		}
		if (((CustomPayloadC2SPacket) packet).payload().id().toString().matches("minecraft:(?!(?:un)?register).*")) {
			return;
		}
		ci.cancel();
	}
}
