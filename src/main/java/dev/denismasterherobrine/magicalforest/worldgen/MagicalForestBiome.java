package dev.denismasterherobrine.magicalforest.worldgen;

import com.minecraftabnormals.abnormals_core.core.util.BiomeUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.BiomeSubRegistryHelper;

import com.mojang.datafixers.util.Pair;

import dev.denismasterherobrine.magicalforest.MagicalForest;
import dev.denismasterherobrine.magicalforest.config.Configuration;
import dev.denismasterherobrine.magicalforest.misc.ColorConstants;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicalForest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagicalForestBiome {

    /*
    *   This is the main class of Magical Forest biome and its sub-biomes.
    *   Using Abnormals Core and referenced a bit of code from Bayou Blues.
    *   Should be practically work without any conflicts, I believe?
    */

    private static final BiomeSubRegistryHelper HELPER = MagicalForest.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome MAGICAL_FOREST = HELPER.createBiome("magical_forest", () -> makeMagicalBiome(-0.205f, 0.32f));
    public static final BiomeSubRegistryHelper.KeyedBiome MAGICAL_FOREST_HILLS = HELPER.createBiome("magical_forest_hills", () -> makeMagicalBiome(-0.13f, 0.44f));

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.6325F - f * 0.1F, 0.44F + f * 0.11F, 1F);
    }

    private static Biome makeMagicalBiome(float depth, float scale) {
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.SWAMP)
                .depth(depth)
                .scale(scale)
                .temperature(0.75F)
                .downfall(1.0F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(ColorConstants.STANDARD_WATER)
                        .waterFogColor(ColorConstants.STANDARD_WATERFOG)
                        .fogColor(0x7AABFA)
                        .skyColor(getSkyColorWithTemperatureModifier(0.7f))
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .foliageColorOverride(ColorConstants.MAGICAL_FOREST_FOLIAGE_COLOR)
                        .grassColorOverride(ColorConstants.MAGICAL_FOREST_GRASS_COLOR)
                        .build())
                .mobSpawnSettings(new MobSpawnInfo.Builder().build())
                .generationSettings((new BiomeGenerationSettings.Builder())
                        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                        .build()).build();
    }

    public static void addHillBiome() {
        BiomeUtil.addHillBiome(MAGICAL_FOREST.getKey(), Pair.of(MAGICAL_FOREST_HILLS.getKey(), 1));
    }

    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MAGICAL_FOREST.getKey(), Configuration.biomeWeightForest.get()));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MAGICAL_FOREST_HILLS.getKey(), Configuration.biomeWeightHills.get()));
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(MAGICAL_FOREST.getKey(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(MAGICAL_FOREST_HILLS.getKey(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.RARE, BiomeDictionary.Type.OVERWORLD);
    }
}

