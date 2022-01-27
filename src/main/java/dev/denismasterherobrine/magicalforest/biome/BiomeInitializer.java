package dev.denismasterherobrine.magicalforest.biome;

import dev.denismasterherobrine.magicalforest.MagicalForest;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class BiomeInitializer {
    public static final ResourceKey<Biome> MAGICAL_FOREST = register("magical_forest");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MagicalForest.MOD_ID, name));
    }
}