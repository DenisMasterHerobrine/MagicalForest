package dev.denismasterherobrine.magicalforest.features;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class FancyOakTreeFeature {
    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier placement) {
        return ImmutableList.<PlacementModifier>builder()
                .add(placement)
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(PlacementUtils.HEIGHTMAP_WORLD_SURFACE)
                .add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier placement) {
        return treePlacementBase(placement).build();
    }

    public static final PlacedFeature FANCY_OAK_TREES = PlacementUtils.register("trees_fancy_oak", TreeFeatures.FANCY_OAK
            .placed(treePlacement(PlacementUtils.countExtra(5, 0.1f, 1))));

    public static void addFancyOakTrees(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FancyOakTreeFeature.FANCY_OAK_TREES);
    }
}
