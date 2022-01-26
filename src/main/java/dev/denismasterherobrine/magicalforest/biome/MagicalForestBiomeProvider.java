package dev.denismasterherobrine.magicalforest.biome;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.function.Consumer;

public class MagicalForestBiomeProvider extends BiomeProvider {
    public MagicalForestBiomeProvider(ResourceLocation name, int overworldWeight)
    {
        super(name, overworldWeight);
    }

    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addBiome(mapper, Climate.Parameter.point(0.35F), Climate.Parameter.point(0.5F), Climate.Parameter.point(1.5F), Climate.Parameter.point(0.6F), Climate.Parameter.point(0.25F), Climate.Parameter.point(0.125F), 0.05F, BiomeInitializer.MAGICAL_FOREST);
        this.addBiomeSimilar(mapper, Biomes.FLOWER_FOREST, BiomeInitializer.MAGICAL_FOREST);
    }
}
