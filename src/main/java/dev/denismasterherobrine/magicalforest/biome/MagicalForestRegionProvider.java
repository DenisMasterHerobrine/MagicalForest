package dev.denismasterherobrine.magicalforest.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class MagicalForestRegionProvider extends Region {
    public static final ResourceLocation LOCATION = new ResourceLocation("minecraft:overworld");

    public MagicalForestRegionProvider(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addBiome(mapper, Climate.Parameter.point(0.35F), Climate.Parameter.point(0.5F), Climate.Parameter.point(0.03F), Climate.Parameter.point(0.6F), Climate.Parameter.point(0.02F), Climate.Parameter.point(0.125F), 0F, BiomeInitializer.MAGICAL_FOREST);
    }
}
