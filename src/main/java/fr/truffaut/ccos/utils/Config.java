package fr.truffaut.ccos.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

public class Config {

    @Getter
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Getter
    private JsonObject rootJson, defaultJson;

    /**
     * Initiate configuration (parse JSON, copy from source if needed, etc...)
     */
    public Config() {
        String configFileName = "config.json";
        File configFile = new File(configFileName);
        URL urlSourceJson = ClassLoader.getSystemResource(configFileName);

        assert urlSourceJson != null;

        try {
            if (!configFile.exists()) { // If root file not found, copy from source
                logger.warn("Config file not found, copying from sources...");
                Files.copy(Paths.get(urlSourceJson.toURI()), configFile.toPath());
            }

            BufferedReader reader = new BufferedReader(new FileReader(urlSourceJson.getPath()));
            defaultJson = gson.fromJson(reader, JsonObject.class);
            reader.close();

            reader = new BufferedReader(new FileReader(configFile.toString()));
            rootJson = gson.fromJson(reader, JsonObject.class);
            reader.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the raw property
     *
     * @param key Property key to get
     * @return The JsonElement if found
     */
    @Nullable
    private JsonElement getRawProperty(String key) {
        return rootJson.get(key);
    }

    /**
     * Get the raw property from source
     *
     * @param key Property key to get
     * @return The JsonElement if found
     */
    @Nullable
    private JsonElement getRawDefaultProperty(String key) {
        return defaultJson.get(key);
    }

    public Optional<String> getProperty(@NotNull String key) {
        JsonElement element = getRawProperty(key);
        return Optional.ofNullable(element == null ? null : element.getAsString());
    }

    public String getPropertyOrDefault(@NotNull String key) {
        Optional<String> optional = getProperty(key);
        return optional.orElseGet(() -> Objects.requireNonNull(getRawDefaultProperty(key)).getAsString());
    }
}
