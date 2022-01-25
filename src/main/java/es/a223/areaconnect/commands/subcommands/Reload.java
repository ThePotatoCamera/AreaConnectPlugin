package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Comando de recarga de configuración
 */
public class Reload implements SubCommand {
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
  public void perform(CommandSender sender, String[] args) {
    if (!sender.hasPermission("areaconnect.reload")) {
      sender.sendMessage(ChatColor.RED + "No tienes permiso para ejecutar este comando.");
    }
    sender.sendMessage(ChatColor.YELLOW + "Recargando configuracion...");
    Configuration.reload();
    sender.sendMessage(ChatColor.GREEN + "Configuracion recargada.");
  }
}
