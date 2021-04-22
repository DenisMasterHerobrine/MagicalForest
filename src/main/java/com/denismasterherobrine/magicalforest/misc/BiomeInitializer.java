package com.denismasterherobrine.magicalforest.misc;

import com.denismasterherobrine.magicalforest.MagicalForest;
import com.denismasterherobrine.magicalforest.biome.MagicalForestBiome;
import com.denismasterherobrine.magicalforest.biome.MagicalForestSurfaceBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInitializer {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
            MagicalForest.MOD_ID);

    public static final RegistryObject<Biome> MAGICAL_FOREST = BIOMES
            .register("magical_forest",
                    () -> new MagicalForestBiome(
                            new Biome.Builder().precipitation(Biome.RainType.RAIN).scale(1.2f).temperature(1.0f)
                                    .waterColor(5263615).waterFogColor(4605695)
                                    .surfaceBuilder(
                                            new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(
                                                    register("magical_forest_surface",
                                                            new MagicalForestSurfaceBuilder(
                                                                    SurfaceBuilderConfig::deserialize)),
                                                    new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(),
                                                            Blocks.DIRT.getDefaultState(),
                                                            Blocks.DIRT.getDefaultState())))
                                    .category(Biome.Category.FOREST).downfall(0.5f).depth(0.12f).parent(null)));

    public static void registerBiomes() {
        registerBiome(MAGICAL_FOREST.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type... types) {
        // the line below will make it spawn in the overworld
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, Config.biomeWeight.get()));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

    @SuppressWarnings("deprecation")
    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn) {
        return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
    }
}
