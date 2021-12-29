package es.a223.areaconnect.commands.subcommands;

import org.bukkit.entity.Player;

/**
 * Base de los subcomandos.
 */
public abstract class SubCommand {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();
    public abstract void perform(Player player, String[] args);
}
