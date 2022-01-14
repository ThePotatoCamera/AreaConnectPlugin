package es.a223.areaconnect.commands.subcommands;

import org.bukkit.entity.Player;

/**
 * Base de los subcomandos.
 */
public abstract class SubCommand {
    /**
     * Gets name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets description.
     *
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Gets usage.
     *
     * @return the usage
     */
    public abstract String getUsage();

    /**
     * Perform.
     *
     * @param player the player
     * @param args   the args
     */
    public abstract void perform(Player player, String[] args);
}
