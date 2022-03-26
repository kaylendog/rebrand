/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dog.kaylen.rebrand.RebrandClientMod;
import dog.kaylen.rebrand.config.RebrandModConfig;
import dog.kaylen.rebrand.config.RebrandModConfigLoader;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.TranslatableText;

/**
 * Configuration menu for when the ModMenu API is available.
 */
public class RebrandModMenu implements ModMenuApi {
	/**
	 * Stored default config options.
	 */
	private static final RebrandModConfig DEFAULTS = new RebrandModConfig();
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent)
					.setTitle(new TranslatableText("title.rebrand.config"));
			// get the entry builder
			ConfigEntryBuilder entryBuilder = builder.entryBuilder();
			ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.rebrand.general"));
			// add the enabled config
			general.addEntry(entryBuilder
					.startBooleanToggle(new TranslatableText("option.rebrand.enable"),
							RebrandClientMod.getInstance().getConfig().enable)
					.setDefaultValue(DEFAULTS.enable).setTooltip(new TranslatableText("tooltip.rebrand.enable"))
					.setSaveConsumer(newValue -> RebrandClientMod.getInstance().getConfig().enable = newValue).build());
			// add the brand name config
			general.addEntry(entryBuilder
					.startStrField(new TranslatableText("option.rebrand.brandName"),
							RebrandClientMod.getInstance().getConfig().brandName)
					.setDefaultValue(DEFAULTS.brandName).setTooltip(new TranslatableText("tooltip.rebrand.brandName"))
					.setSaveConsumer(newValue -> RebrandClientMod.getInstance().getConfig().brandName = newValue)
					.build());
			// save the config
			builder.setSavingRunnable(RebrandModConfigLoader::saveConfig);
			return builder.build();
		};
	}
}
