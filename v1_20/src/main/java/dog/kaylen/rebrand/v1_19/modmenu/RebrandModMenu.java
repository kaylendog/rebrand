/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand.v1_19.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dog.kaylen.rebrand.RebrandClientMod;
import dog.kaylen.rebrand.config.RebrandModConfig;
import dog.kaylen.rebrand.config.RebrandModConfigLoader;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

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
					.setTitle(Text.translatable("title.rebrand.config"));
			// get the entry builder
			ConfigEntryBuilder entryBuilder = builder.entryBuilder();
			ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.rebrand.general"));
			// add the enabled config
			general.addEntry(entryBuilder
					.startBooleanToggle(Text.translatable("option.rebrand.enable"),
							RebrandClientMod.getInstance().getConfig().enable)
					.setDefaultValue(DEFAULTS.enable).setTooltip(Text.translatable("tooltip.rebrand.enable"))
					.setSaveConsumer(newValue -> RebrandClientMod.getInstance().getConfig().enable = newValue).build());
			// add the brand name config
			general.addEntry(entryBuilder
					.startStrField(Text.translatable("option.rebrand.brandName"),
							RebrandClientMod.getInstance().getConfig().brandName)
					.setDefaultValue(DEFAULTS.brandName).setTooltip(Text.translatable("tooltip.rebrand.brandName"))
					.setSaveConsumer(newValue -> RebrandClientMod.getInstance().getConfig().brandName = newValue)
					.build());
			// add the ghost mode config
			general.addEntry(entryBuilder
					.startBooleanToggle(Text.translatable("option.rebrand.ghostMode"),
							RebrandClientMod.getInstance().getConfig().ghostMode)
					.setDefaultValue(DEFAULTS.ghostMode).setTooltip(Text.translatable("tooltip.rebrand.ghostMode"))
					.setSaveConsumer(newValue -> RebrandClientMod.getInstance().getConfig().ghostMode = newValue)
					.build());
			// save the config
			builder.setSavingRunnable(RebrandModConfigLoader::saveConfig);
			return builder.build();
		};
	}
}
