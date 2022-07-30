package dev.denismasterherobrine.magicalforest.biome;

import dev.denismasterherobrine.magicalforest.MagicalForest;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeRegistry {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MagicalForest.MOD_ID);

    public static void registerBiomes()
    {
        register(BiomeInitializer.MAGICAL_FOREST, MagicalForestBiomeDecorator::decorateMagicalForest);
    }

    public static RegistryObject<Biome> register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier)
    {
        return BIOMES.register(key.location().getPath(), biomeSupplier);
    }
}