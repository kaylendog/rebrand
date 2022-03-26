/*
 * Copyright Kaylen Dart 2022
 * This project is licensed under the GNU GPLv3 license. See the LICENSE file for more information.
 */
package dog.kaylen.rebrand.config;

/**
 * A data class containing the Rebrand mod configuration.
 */
public class RebrandModConfig {
	/**
	 * The name of the brand that is sent to the server. Defaults to vanilla.
	 */
	public String brandName = "vanilla";
	/**
	 * Enables the sending of the custom brand.
	 */
	public Boolean enable = true;
}
