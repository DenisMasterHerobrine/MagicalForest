package dev.denismasterherobrine.magicalforest;

import dev.denismasterherobrine.magicalforest.biome.BiomeRegistry;
import dev.denismasterherobrine.magicalforest.biome.MagicalForestBiomeProvider;
import dev.denismasterherobrine.magicalforest.configuration.ConfigManager;
import dev.denismasterherobrine.magicalforest.configuration.Configuration;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;

public class MagicalForest implements ModInitializer, TerraBlenderApi {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MagicalForest.MOD_ID);
	public static final String MOD_ID = "magicalforest";
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		BiomeRegistry.registerBiomes();
		ConfigManager.registerAutoConfig();
		Configuration config = AutoConfig.getConfigHolder(Configuration.class).getConfig();
		try {
			config.validatePostLoad();
		} catch (ConfigData.ValidationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTerraBlenderInitialized() {
		if (ConfigManager.getConfig().biomeWeight != 0){
			BiomeProviders.register(new MagicalForestBiomeProvider(new ResourceLocation(MOD_ID, "magical_forest_biome_provider"), ConfigManager.getConfig().biomeWeight));
		}
		else LOGGER.info("Magical Forest biome is disabled in the config! Please change 0 to something bigger to re-enable it.");
	}
}
