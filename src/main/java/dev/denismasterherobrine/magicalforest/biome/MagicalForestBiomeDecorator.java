package dev.denismasterherobrine.magicalforest.biome;

import dev.denismasterherobrine.magicalforest.features.FancyOakTreeFeature;
import dev.denismasterherobrine.magicalforest.util.ColorConstants;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class MagicalForestBiomeDecorator {

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float f = temperature / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.6325F - f * 0.1F, 0.44F + f * 0.11F, 1F);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
    {
        return biome(precipitation, category, temperature, downfall, ColorConstants.STANDARD_WATER, ColorConstants.STANDARD_WATERFOG, ColorConstants.MAGICAL_FOREST_FOLIAGE_COLOR, ColorConstants.MAGICAL_FOREST_GRASS_COLOR, spawnBuilder, biomeBuilder);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, int grassColor, int foliageColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
    {
        return (new Biome.BiomeBuilder())
                .precipitation(precipitation)
                .biomeCategory(category)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(waterColor)
                        .waterFogColor(waterFogColor)
                        .fogColor(12638463)
                        .skyColor(getSkyColorWithTemperatureModifier(temperature))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .foliageColorOverride(foliageColor)
                        .grassColorOverride(grassColor)
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }

    public static Biome decorateMagicalForest() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeFeatures = new BiomeGenerationSettings.Builder();

        spawnSettings.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 5, 2, 6));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 80, 4, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 90, 4, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 85, 4, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 7, 1, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
        spawnSettings.addSpawn(MobCategory.AXOLOTLS, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 30, 1, 3));

        // I really now hate feature cycle in 1.18... is there anything providing a feature cycle documentation?

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeFeatures);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeFeatures);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeFeatures);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeFeatures);
        BiomeDefaultFeatures.addDefaultSprings(biomeFeatures);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeFeatures);

        BiomeDefaultFeatures.addDefaultOres(biomeFeatures);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeFeatures);

        BiomeDefaultFeatures.addForestFlowers(biomeFeatures);
        BiomeDefaultFeatures.addWarmFlowers(biomeFeatures);
        BiomeDefaultFeatures.addWaterTrees(biomeFeatures);
        BiomeDefaultFeatures.addGroveTrees(biomeFeatures);
        BiomeDefaultFeatures.addFerns(biomeFeatures);

        BiomeDefaultFeatures.addMossyStoneBlock(biomeFeatures);

        BiomeDefaultFeatures.addMushroomFieldVegetation(biomeFeatures);
        BiomeDefaultFeatures.addMeadowVegetation(biomeFeatures);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeFeatures);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeFeatures);
        BiomeDefaultFeatures.addCommonBerryBushes(biomeFeatures);
        BiomeDefaultFeatures.addJungleMelons(biomeFeatures);

        FancyOakTreeFeature.addFancyOakTrees(biomeFeatures);

        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.5F, 0.7F, ColorConstants.STANDARD_WATER, ColorConstants.STANDARD_WATERFOG, ColorConstants.MAGICAL_FOREST_FOLIAGE_COLOR, ColorConstants.MAGICAL_FOREST_GRASS_COLOR, spawnSettings, biomeFeatures);
    }
}
