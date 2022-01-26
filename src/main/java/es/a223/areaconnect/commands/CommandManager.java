package es.a223.areaconnect.commands;

import es.a223.areaconnect.commands.subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * Command manager para gestionar los subcomandos del plugin.
 */
public class CommandManager implements CommandExecutor, TabCompleter {

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
    List<String> completions = new ArrayList<String>();
    for (SubCommand subCommand : this.getSubcommands()) {
      completions.add(subCommand.getName());
    }

    List<String> results = new ArrayList<String>();

    if (args.length == 1) {
      for (String c : completions) {
        if (c.toLowerCase().startsWith(args[0].toLowerCase())) {
          results.add(c);
        }
      }
      return results;
    }

    return null;
  }
}
