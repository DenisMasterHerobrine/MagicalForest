package com.denismasterherobrine.magicalforest.misc;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue biomeWeight;
    public static ForgeConfigSpec.BooleanValue blueForest;

    static {
        COMMON_BUILDER.comment("General Biome Configuration").push(CATEGORY_GENERAL);
        biomeWeight = COMMON_BUILDER.comment("Magical Forest spawn weight in Overworld. The lower the number, the rarer is the biome!").defineInRange("biomeWeight", 12, 1,2147000);
        blueForest = COMMON_BUILDER.comment("This option brings back old Thaumcraft blue-like Magical Forest look.").define("blueForest", false);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}