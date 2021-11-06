package dev.denismasterherobrine.magicalforest.misc;

import dev.denismasterherobrine.magicalforest.config.Configuration;

public class ColorConstants {

    public static final int STANDARD_WATER = 0x3F76E4;

    public static final int STANDARD_WATERFOG = 0x050533;

    public static int MAGICAL_FOREST_FOLIAGE_COLOR = 0x3AC280;

    public static int MAGICAL_FOREST_GRASS_COLOR = 0x22D469;

    private ColorConstants() {
        if (Configuration.blueForest.get()){
            MAGICAL_FOREST_FOLIAGE_COLOR = 0x27424E;
            MAGICAL_FOREST_GRASS_COLOR = 0x426E85;
        }
    }
}