package es.a223.areaconnect;

import es.a223.areaconnect.commands.CommandManager;
import es.a223.areaconnect.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class AreaConnect extends JavaPlugin {

    @Override
    public void onEnable() {

        // Config initialization
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Setup config
        getLogger().finer("Recuperando configuracion...");
        Configuration.setup();
        Configuration.get().addDefault("DiscordConnection", false);
        Configuration.get().options().copyDefaults(true);
        Configuration.save();

        // Init commands
        getLogger().finer("Inicializando comandos...");
        CommandManager commands = new CommandManager();
        Objects.requireNonNull(getCommand("areaconnect")).setExecutor(commands);

        // MariaDB connection
        getLogger().info("Configurando la base de datos...");

        try {
            Session testingSession = this.dbConnection().openSession();
            if (!testingSession.isOpen()) {
                throw new HibernateException("No se ha podido conectar a la base de datos.");
            }
            testingSession.close();
        } catch (HibernateException e) {
            getLogger().severe(ChatColor.RED + "Ha habido un error con la base de datos. Deshabilitando para evitar problemas.");
            getLogger().severe("Deberias revisar la configuracion de la base de datos.");
            getLogger().severe(ChatColor.YELLOW + "Si todo esta correcto, reporta este error en " + ChatColor.AQUA + "https://github.com/area-223/AreaConnectPlugin/issues");
            throw new RuntimeException(e);
        }

        getLogger().info("AreaConnect habilitado con exito!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Cerrando conexion con la base de datos...");
        getLogger().info("AreaConnect deshabilitado.");
    }


    public final SessionFactory dbConnection() {
        org.hibernate.cfg.Configuration hibconfig = new org.hibernate.cfg.Configuration();
        hibconfig.setProperty("hibernate.connection.datasource", "jdbc:mariadb://" + Configuration.get().getString("database.host") + ":" + Configuration.get().getString("database.port") + "/" + Configuration.get().getString("database.database"));
        hibconfig.setProperty("hibernate.connection.username", Configuration.get().getString("database.user"));
        hibconfig.setProperty("hibernate.connection.password", Configuration.get().getString("database.password"));
        hibconfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        hibconfig.setProperty("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
        hibconfig.setProperty("hibernate.show_sql", "true");
        hibconfig.addAnnotatedClass(es.a223.areaconnect.models.Users.class);
        hibconfig.addAnnotatedClass(es.a223.areaconnect.models.UserLink.class);

        return hibconfig.buildSessionFactory();
    }
}
