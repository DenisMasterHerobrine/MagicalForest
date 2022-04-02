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

    static {
        COMMON_BUILDER.comment("General Magical Forest Biome Configuration").push(CATEGORY_GENERAL);
        biomeWeightForest = COMMON_BUILDER.comment("Magical Forest spawn weight in Overworld. The lower the number, the rarer is the biome. Set 0 to disable this biome.").defineInRange("biomeWeightForest", 1, 0,2147483647);
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
