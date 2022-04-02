package dev.denismasterherobrine.magicalforest.configuration;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

public class ConfigManager {
    private static ConfigHolder<Configuration> holder;

    public static void registerAutoConfig() {
        if (holder != null) {
            throw new IllegalStateException("Configuration already registered!");
        }

        holder = AutoConfig.register(Configuration.class, Toml4jConfigSerializer::new);
        holder.save();
    }

    public static Configuration getConfig() {
        if (holder == null) {
            return new Configuration();
        }

        return holder.getConfig();
    }

    public static void load() {
        if (holder == null) {
            registerAutoConfig();
        }

        holder.load();
    }

    public static void save() {
        if (holder == null) {
            registerAutoConfig();
        }

        holder.save();
    }
}
