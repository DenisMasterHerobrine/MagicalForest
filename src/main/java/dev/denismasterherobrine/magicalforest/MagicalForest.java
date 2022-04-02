package dev.denismasterherobrine.magicalforest;

import dev.denismasterherobrine.magicalforest.biome.MagicalForestRegionProvider;
import dev.denismasterherobrine.magicalforest.config.Configuration;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import terrablender.api.Regions;

import java.util.stream.Collectors;

@Mod("magicalforest")
@Mod.EventBusSubscriber(modid = MagicalForest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagicalForest {

    public static final String MOD_ID = "magicalforest";

    private static final Logger LOGGER = LogManager.getLogger();

        public MagicalForest() {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

            Configuration.loadConfig(Configuration.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("MagicalForest-common.toml"));
        }

        private void setup(final FMLCommonSetupEvent event)
        {
            if (!Configuration.biomeWeightForest.get().equals(0)){
                // Given we only add one biome, we should keep our weight relatively low.
                Regions.register(new MagicalForestRegionProvider(new ResourceLocation(MOD_ID, "overworld"), Configuration.biomeWeightForest.get()));
            }
            else LOGGER.info("Magical Forest biome is disabled in the config! Please change 0 to something bigger to re-enable it.");
        }

        private void processIMC(final InterModProcessEvent event)
        {
            LOGGER.info("Got IMC {}", event.getIMCStream().
                    map(m->m.getMessageSupplier().get()).
                    collect(Collectors.toList()));
        }
    }
