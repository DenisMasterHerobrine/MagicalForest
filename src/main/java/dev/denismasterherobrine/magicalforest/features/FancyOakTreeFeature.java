package dev.denismasterherobrine.magicalforest.features;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;

public class FancyOakTreeFeature {

    /*
    *  This is a bit tricky tree feature workaround for Abnormals Core biome generation.
    *  Probably will be removed once there will be a better solution of changing weight values for FANCY_OAK generator parameter.
    *  If you want to contribute to this project - feel free to PR a better algorithm of adding own FANCY_OAK tree feature placement.
    */

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String parameter, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, parameter, configuredFeature);
    }

    protected static final BlockState OAK_LOG = Blocks.OAK_LOG.defaultBlockState();
    protected static final BlockState OAK_LEAVES = Blocks.OAK_LEAVES.defaultBlockState();

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> OAK = register("oak", Feature.TREE
            .configured((new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(FancyOakTreeFeature.OAK_LOG),
                    new SimpleBlockStateProvider(FancyOakTreeFeature.OAK_LEAVES),
                    new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                    new StraightTrunkPlacer(4, 2, 0),
                    new TwoLayerFeature(1, 0, 1)))
                    .ignoreVines()
                    .build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_OAK = register("fancy_oak", Feature.TREE
            .configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(FancyOakTreeFeature.OAK_LOG),
                    new SimpleBlockStateProvider(FancyOakTreeFeature.OAK_LEAVES),
                    new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                    new FancyTrunkPlacer(3, 11, 0),
                    new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
            .ignoreVines()
            .heightmap(Heightmap.Type.MOTION_BLOCKING)
            .build()));

    public static final ConfiguredFeature<?, ?> MAGICAL_FANCY_OAK = register("magical_fancy_oak", Feature.RANDOM_SELECTOR
            .configured(new MultipleRandomFeatureConfig(ImmutableList.of(FANCY_OAK
                    .weighted(20.5F)), OAK))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA
                    .configured(new AtSurfaceWithExtraConfig(0, 40.75F, 10))));

    public static void addMagicalFancyOakTrees(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, MAGICAL_FANCY_OAK);
    }
}
