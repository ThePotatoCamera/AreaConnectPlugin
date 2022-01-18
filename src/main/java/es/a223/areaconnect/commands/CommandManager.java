package es.a223.areaconnect.commands;

import es.a223.areaconnect.commands.subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Command manager para gestionar los subcomandos del plugin.
 */
public class CommandManager implements CommandExecutor {

  private final ArrayList<SubCommand> subCommands = new ArrayList<>();

  /**
   * Instantiates a new Command manager.
   */
  public CommandManager() {
    subCommands.add(new Reload());
    subCommands.add(new AccountLink());
    subCommands.add(new AccountUnlink());
    subCommands.add(new AddMoney());
    subCommands.add(new RemoveMoney());
    subCommands.add(new SetMoney());
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
      sender.sendMessage(ChatColor.GOLD + "-= Comandos disponibles de AreaConnect =-");
      for (SubCommand subCommand : subCommands) {
        sender.sendMessage(ChatColor.GOLD + "/areaconnect " + subCommand.getName() + ": " + ChatColor.WHITE + subCommand.getDescription());
      }
    }
    else {
      for (SubCommand subCommand : subCommands) {
        if (args[0].equalsIgnoreCase(subCommand.getName())) {
          subCommand.perform(sender, args);
        }
      }
    }
    return true;
  }


  /**
   * Gets subcommands.
   *
   * @return the subcommands
   */
  public ArrayList<SubCommand> getSubcommands() {
    return subCommands;
  }
}
