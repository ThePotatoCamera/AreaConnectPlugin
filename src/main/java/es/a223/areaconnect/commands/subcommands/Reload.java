package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Recarga los archivos de configuracion";
    }

    @Override
    public String getUsage() {
        return "/areaconnect reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("areaconnect.reload")) {
            player.sendMessage(ChatColor.YELLOW + "Recargando configuracion...");
            Configuration.reload();
            player.sendMessage(ChatColor.GREEN + "Configuracion recargada.");
        } else {
            player.sendMessage(ChatColor.RED + "No tienes permiso para ejecutar este comando.");
        }
    }
}
