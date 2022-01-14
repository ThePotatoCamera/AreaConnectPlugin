package es.a223.areaconnect.configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Configuraciones del plugin
 */
public class Configuration {
    private static File file;
    private static FileConfiguration config;

    /**
     * Setup.
     */
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("AreaConnect").getDataFolder(), "config.yml");
        if (!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Get file configuration.
     *
     * @return the file configuration
     */
    public static FileConfiguration get() {
        return config;
    }

    /**
     * Save.
     */
    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reload.
     */
    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
