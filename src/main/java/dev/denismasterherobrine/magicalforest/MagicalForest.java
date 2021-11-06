package dev.denismasterherobrine.magicalforest;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;

import dev.denismasterherobrine.magicalforest.config.Configuration;
import dev.denismasterherobrine.magicalforest.worldgen.MagicalForestBiome;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod("magicalforest")
@Mod.EventBusSubscriber(modid = MagicalForest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagicalForest {

    public static final String MOD_ID = "magicalforest";

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    private static final Logger LOGGER = LogManager.getLogger();

        public MagicalForest() {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

            MinecraftForge.EVENT_BUS.register(this);

            REGISTRY_HELPER.register(FMLJavaModLoadingContext.get().getModEventBus());

            Configuration.loadConfig(Configuration.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("MagicalForest-common.toml"));
        }

        private void setup(final FMLCommonSetupEvent event)
        {
            event.enqueueWork(() -> {
                MagicalForestBiome.addBiomeTypes();
                MagicalForestBiome.registerBiomesToDictionary();
                MagicalForestBiome.addHillBiome();
            });
        }

        private void processIMC(final InterModProcessEvent event)
        {
            LOGGER.info("Got IMC {}", event.getIMCStream().
                    map(m->m.getMessageSupplier().get()).
                    collect(Collectors.toList()));
        }
    }
