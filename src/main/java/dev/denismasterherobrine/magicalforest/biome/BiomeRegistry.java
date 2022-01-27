package dev.denismasterherobrine.magicalforest.biome;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeRegistry {
    public static void registerBiomes()
    {
        register(BiomeInitializer.MAGICAL_FOREST, MagicalForestBiomeDecorator.decorateMagicalForest());
    }

    private static Biome register(ResourceKey<Biome> key, Biome biome)
    {
        return BuiltinRegistries.registerMapping(BuiltinRegistries.BIOME, key, biome);
    }
}