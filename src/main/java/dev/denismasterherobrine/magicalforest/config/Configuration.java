package dev.denismasterherobrine.magicalforest.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Configuration {

    public static final String CATEGORY_GENERAL = "general";
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue biomeWeightForest;
    public static ForgeConfigSpec.IntValue biomeWeightHills;
    public static ForgeConfigSpec.BooleanValue blueForest;

    static {
        COMMON_BUILDER.comment("General Magical Forest Biome Configuration").push(CATEGORY_GENERAL);
        biomeWeightForest = COMMON_BUILDER.comment("Magical Forest spawn weight in Overworld. The lower the number, the rarer is the biome!").defineInRange("biomeWeight", 20, 1,2147483647);
        biomeWeightHills = COMMON_BUILDER.comment("Magical Forest Hills spawn weight in Overworld. The lower the number, the rarer is the biome!").defineInRange("biomeWeight", 12, 1,2147483647);
        blueForest = COMMON_BUILDER.comment("This option brings back old Thaumcraft 2 blue-like Magical Forest look.").define("blueForest", false);
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
