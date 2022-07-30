package dev.denismasterherobrine.magicalforest.util;

import dev.denismasterherobrine.magicalforest.MagicalForest;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TagsProvider {
    // If Thaumcraft ever comes, TC7+ compat could be easy added by adding magical_forests tag to Magical Forest biome.
    public static TagKey<Biome> MAGICAL_FORESTS =
            TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MagicalForest.MOD_ID, "magical_forests"));
}
