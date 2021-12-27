package es.a223.areaconnect;

import es.a223.areaconnect.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class AreaConnect extends JavaPlugin {
    @Override
    public void onEnable() {

        // Config initialization
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Configuration.setup();
        Configuration.get().addDefault("DiscordConnection", false);
        Configuration.get().options().copyDefaults(true);
        Configuration.save();
        getLogger().info("AreaConnect has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AreaConnect has been disabled!");
    }
}
