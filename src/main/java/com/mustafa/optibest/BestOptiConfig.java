package com.mustafa.optibest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

public class BestOptiConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("bestopti.json");

    private static BestOptiConfig INSTANCE = new BestOptiConfig();

    // Ayarlar
    public int renderDistance = 8;
    public int entityDistance = 48;
    public boolean limitParticles = true;
    public int maxParticles = 100;
    public boolean fpsBoostMode = false;

    public static BestOptiConfig get() {
        return INSTANCE;
    }

    public static void load() {
        try {
            if (CONFIG_PATH.toFile().exists()) {
                Reader reader = new FileReader(CONFIG_PATH.toFile());
                INSTANCE = GSON.fromJson(reader, BestOptiConfig.class);
                reader.close();
            } else {
                save();
            }
        } catch (Exception e) {
            System.err.println("[BestOpti] Config yüklenemedi: " + e.getMessage());
            INSTANCE = new BestOptiConfig();
        }
    }

    public static void save() {
        try {
            Writer writer = new FileWriter(CONFIG_PATH.toFile());
            GSON.toJson(INSTANCE, writer);
            writer.close();
        } catch (Exception e) {
            System.err.println("[BestOpti] Config kaydedilemedi: " + e.getMessage());
        }
    }

    public static void apply() {
        // Ayarları oyuna uygula
        net.minecraft.client.MinecraftClient client =
            net.minecraft.client.MinecraftClient.getInstance();
        if (client == null || client.options == null) return;

        client.options.getViewDistance().setValue(INSTANCE.renderDistance);
    }
}
