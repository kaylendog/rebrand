package dog.kaylen.rebrand.mixin.v1_19;

import dog.kaylen.rebrand.RebrandClientMod;
import dog.kaylen.rebrand.config.RebrandModConfig;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Injects the desired client brand into the ClientBrandRetriever.
 */
@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {
    @Inject(at = @At("HEAD"), method = "getClientModName", cancellable = true, remap = false)
    private static void getConfiguredClientBrand(CallbackInfoReturnable<String> info) {
        // prevent npe on client initialization
        if (RebrandClientMod.getInstance() == null) {
            info.setReturnValue("fabric");
            return;
        }
        RebrandModConfig config = RebrandClientMod.getInstance().getConfig();
        // if custom brand is not enabled, return before setting
        if (!config.enable) {
            return;
        }
        info.setReturnValue(config.brandName);
    }
}
