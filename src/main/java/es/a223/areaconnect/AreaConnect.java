package es.a223.areaconnect;

import es.a223.areaconnect.commands.CommandManager;
import es.a223.areaconnect.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class AreaConnect extends JavaPlugin {
    @Override
    public void onEnable() {

        // Config initialization
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Setup config
        Configuration.setup();
        Configuration.get().addDefault("DiscordConnection", false);
        Configuration.get().options().copyDefaults(true);
        Configuration.save();

        // Init commands
        CommandManager commands = new CommandManager();
        Objects.requireNonNull(getCommand("areaconnect")).setExecutor(commands);

        // MariaDB connection
        MariaDbPoolDataSource poolDataSource = new MariaDbPoolDataSource();

        try {
            poolDataSource.setServerName(Configuration.get().getString("database.host"));
            poolDataSource.setPort(Configuration.get().getInt("database.port"));
            poolDataSource.setUser(Configuration.get().getString("database.user"));
            poolDataSource.setPassword(Configuration.get().getString("database.password"));
            poolDataSource.setDatabaseName(Configuration.get().getString("database.database"));
        } catch (SQLException e) {
            getLogger().severe("Error al conectar con la base de datos: " + e.getMessage());
        }

        try {
            Statement statement = poolDataSource.getConnection().createStatement();
            statement.addBatch("CREATE TABLE IF NOT EXISTS `users` (" +
                    "id int auto_increment " +
                    "primary key, " +
                    "username varchar(255) null, " +
                    "uuid varchar(255) not null," +
                    "constraint users_id_uindex " +
                    "unique (id), " +
                    "constraint users_uuid_uindex " +
                    "unique (uuid) " +
                    ");" );
            statement.addBatch("create table if not exists `user_link`" +
                    "(" +
                    "id int auto_increment " +
                    "primary key, " +
                    "user_id int null, " +
                    "discord_id varchar(255) null, " +
                    "constraint user_link_discord_id_uindex " +
                    "unique (discord_id), " +
                    "constraint user_link_id_uindex " +
                    "unique (id), " +
                    "constraint user_link_user_id_uindex " +
                    "unique (user_id), " +
                    "constraint user_link_users_id_fk " +
                    "foreign key (user_id) references users (id) " +
                    "on update cascade on delete cascade " +
                    ");");
            statement.executeBatch();
            statement.close();
        }
        catch (SQLException e) {
            getLogger().severe("Error al crear la tabla: " + e.getMessage());
        }

        getLogger().info(ChatColor.GREEN + "AreaConnect habilitado con exito!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AreaConnect deshabilitado, buenas noches.");
    }
}
