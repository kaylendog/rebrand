/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand;

import dog.kaylen.rebrand.config.RebrandModConfig;
import dog.kaylen.rebrand.config.RebrandModConfigLoader;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RebrandClientMod implements ClientModInitializer {
	private final Logger logger = LogManager.getLogger("rebrand");
	/**
	 * Get the mod logger.
	 * 
	 * @return The mod logger.
	 */
	public Logger getLogger() {
		return logger;
	}
	/**
	 * The mod configuration.
	 */
	private RebrandModConfig config = new RebrandModConfig();
	/**
	 * Get the mod configuration.
	 * 
	 * @return The mod configuration.
	 */
	public RebrandModConfig getConfig() {
		return config;
	}
	private static RebrandClientMod instance;
	/**
	 * Get the mod instance.
	 * 
	 * @return The mod instance.
	 */
	public static RebrandClientMod getInstance() {
		return instance;
	}
	@Override
	public void onInitializeClient() {
		// set the singleton instance
		instance = this;
		// initialize the mod configuration
		config = RebrandModConfigLoader.tryLoad();
		this.logger.info("Rebrand initialized - brand on startup: '" + config.brandName + "'");
	}
}
