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
  public void perform(CommandSender sender, String[] args) {
    if (!sender.hasPermission("areaconnect.reload")) {
      sender.sendMessage(ChatColor.RED + "No tienes permiso para ejecutar este comando.");
    }
    sender.sendMessage(ChatColor.YELLOW + "Recargando configuracion...");
    Configuration.reload();
    sender.sendMessage(ChatColor.GREEN + "Configuracion recargada.");
  }

  /**
   * Requests a list of possible completions for a command argument.
   *
   * @param sender  Source of the command.  For players tab-completing a
   *                command inside of a command block, this will be the player, not
   *                the command block.
   * @param command Command which was executed
   * @param alias   The alias used
   * @param args    The arguments passed to the command, including final
   *                partial argument to be completed and command label
   * @return A List of possible completions for the final argument, or null
   * to default to the command executor
   */
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    return null;
  }
}
