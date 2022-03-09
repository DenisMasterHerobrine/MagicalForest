package dev.denismasterherobrine.magicalforest.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import terrablender.worldgen.DefaultOverworldRegion;

import java.util.function.Consumer;

public class MagicalForestBiomeProvider extends DefaultOverworldRegion {
    public static final ResourceLocation LOCATION = new ResourceLocation("minecraft:overworld");

    public MagicalForestBiomeProvider(int overworldWeight)
    {
        super(overworldWeight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addBiome(mapper, Climate.Parameter.point(0.35F), Climate.Parameter.point(0.5F), Climate.Parameter.point(1.5F), Climate.Parameter.point(0.6F), Climate.Parameter.point(0.25F), Climate.Parameter.point(0.125F), 0F, BiomeInitializer.MAGICAL_FOREST);
        this.addBiomeSimilar(mapper, Biomes.FLOWER_FOREST, BiomeInitializer.MAGICAL_FOREST);
    }
}
