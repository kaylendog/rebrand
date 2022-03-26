/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import dog.kaylen.rebrand.RebrandClientMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;

/**
 * Represents the mod configuration.
 */
public class RebrandModConfigLoader {
	/**
	 * The configuration file name.
	 */
	static String CONFIG_NAME = "rebrand.toml";
	/**
	 * The TOML writer.
	 */
	static TomlWriter TOML_WRITER = new TomlWriter();
	/**
	 * Attempts to load the mod configuration.
	 * 
	 * @return The mod configuration or the default config if loading fails.
	 */
	public static RebrandModConfig tryLoad() {
		File file = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_NAME).toFile();
		RebrandModConfig defaults = new RebrandModConfig();
		// if file doesn't exist, copy the defaults
		if (!file.exists()) {
			try {
				TOML_WRITER.write(defaults, file);
			} catch (IOException ex) {
				// warn if writing fails
				RebrandClientMod.getInstance().getLogger()
						.warn("Failed to load configuration from filesystem, using defaults. More info is below.");
				RebrandClientMod.getInstance().getLogger().warn(ex);
			}
			return defaults;
		}
		// parse TOML and cast to config class
		return new Toml().read(file).to(RebrandModConfig.class);
	}
	/**
	 * Save the in-memory config to disk
	 * @return True if the action was successful, false otherwise.
	 */
	public static Boolean saveConfig() {
		RebrandClientMod.getInstance().getLogger().info("Saving configuration to disk...");
		RebrandClientMod.getInstance().getLogger().info("Brand name = " + RebrandClientMod.getInstance().getConfig().brandName);
		File file = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_NAME).toFile();
		try {
			TOML_WRITER.write(RebrandClientMod.getInstance().getConfig(), file);
		} catch(IOException ex) {
			RebrandClientMod.getInstance().getLogger().error("Failed to save configuration to disk - more info below");
			RebrandClientMod.getInstance().getLogger().error(ex);
			return false;
		}
		return true;
	}
}
