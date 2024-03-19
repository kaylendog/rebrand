/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand.mixins;

import dog.kaylen.rebrand.RebrandClientMod;
import dog.kaylen.rebrand.config.RebrandModConfig;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponsePayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Prevents the client from sending a response to the server when ghost mode is enabled.
 */
@Mixin(LoginQueryResponseC2SPacket.class)
public class LoginQueryResponseC2SPacketMixin {
	@Inject(at = @At("TAIL"), method = "response()Lnet/minecraft/network/packet/c2s/login/LoginQueryResponsePayload;")
	private void rebrand$response(CallbackInfoReturnable<LoginQueryResponsePayload> info) {
		// default to ghost mode if the mod is not initialized - shouldn't occur!
		if (RebrandClientMod.getInstance() == null) {
			info.setReturnValue(null);
		}
		RebrandModConfig config = RebrandClientMod.getInstance().getConfig();
		if (!config.enable || !config.ghostMode) {
			return;
		}
		info.setReturnValue(null);
	}

}
