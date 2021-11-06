package dev.denismasterherobrine.magicalforest.worldgen;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;

import dev.denismasterherobrine.magicalforest.MagicalForest;
import dev.denismasterherobrine.magicalforest.features.FancyOakTreeFeature;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.DefaultBiomeFeatures;

import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicalForest.MOD_ID)
public class MagicalForestBiomeFeatures {

    /*
    *   There you can find all features related to Magical Forest biome and its sub-biomes.
    *   Including Magical Fancy Oak Tree, Silverwood (WIP) and Greatwood (WIP) feature generation.
    *   Probably some changes can arise once rebranded.
    */

    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (biomeName == null)
            return;

        if (DataUtil.matchesKeys(biomeName, MagicalForestBiome.MAGICAL_FOREST.getKey(), MagicalForestBiome.MAGICAL_FOREST_HILLS.getKey())) {
            addMagicalForestFeatures(event.getGeneration(), event.getSpawns());
        }
    }

    public static void addMagicalForestFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnInfoBuilder spawns) {
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDefaultOverworldOceanStructures(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);

        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addForestFlowers(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addBerryBushes(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        DefaultBiomeFeatures.addWarmFlowers(builder);
        DefaultBiomeFeatures.addSavannaExtraGrass(builder);
        DefaultBiomeFeatures.addLukeWarmKelp(builder);
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addDefaultSeagrass(builder);
        DefaultBiomeFeatures.addSparseBerryBushes(builder);
        DefaultBiomeFeatures.addBadlandGrass(builder);
        DefaultBiomeFeatures.addBadlandsTrees(builder);

        DefaultBiomeFeatures.addPlainVegetation(builder);

        DefaultBiomeFeatures.addSurfaceFreezing(builder);

        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addExtraEmeralds(builder);
        DefaultBiomeFeatures.addExtraGold(builder);
        DefaultBiomeFeatures.addFossilDecoration(builder);
        DefaultBiomeFeatures.addInfestedStone(builder);
        DefaultBiomeFeatures.addMossyStoneBlock(builder);
        DefaultBiomeFeatures.addDefaultCarvers(builder);
        DefaultBiomeFeatures.addOceanCarvers(builder);
        DefaultBiomeFeatures.addDesertLakes(builder);

        DefaultBiomeFeatures.addDefaultSoftDisks(builder);
        DefaultBiomeFeatures.addSwampClayDisk(builder);

        DefaultBiomeFeatures.addPlainVegetation(builder);
        DefaultBiomeFeatures.addDefaultExtraVegetation(builder);
        DefaultBiomeFeatures.addMushroomFieldVegetation(builder);

        DefaultBiomeFeatures.addJungleGrass(builder);

        FancyOakTreeFeature.addMagicalFancyOakTrees(builder);

        DefaultBiomeFeatures.commonSpawns(spawns);
        DefaultBiomeFeatures.farmAnimals(spawns);
        DefaultBiomeFeatures.ambientSpawns(spawns);
        DefaultBiomeFeatures.plainsSpawns(spawns);
    }
}
