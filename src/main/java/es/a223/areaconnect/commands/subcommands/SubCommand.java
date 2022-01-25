package es.a223.areaconnect.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

/**
 * Base de los subcomandos.
 */
public interface SubCommand {
  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName();

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription();
  /**
   * Gets usage.
   *
   * @return the usage
   */
  public String getUsage();

  /**
   * Perform.
   *
   * @param player the player
   * @param args   the args
   */
  public void perform(CommandSender player, String[] args);
}
