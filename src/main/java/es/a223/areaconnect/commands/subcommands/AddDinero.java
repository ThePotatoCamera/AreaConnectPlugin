package es.a223.areaconnect.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AddDinero extends SubCommand {
    /**
     * Gets name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "adddinero";
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Agrega dinero a un jugador";
    }

    /**
     * Gets usage.
     *
     * @return the usage
     */
    @Override
    public String getUsage() {
        return "/areaconnect adddinero [jugador] <dinero>";
    }

    /**
     * Perform.
     *
     * @param player the player
     * @param args   the args
     */
    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.GOLD + "Uso: " + getUsage());
            return;
        }
        if (!player.hasPermission("areaconnect.adddinero")) {
            player.sendMessage(ChatColor.RED + "No tienes permiso para usar este comando");
            return;
        }
        Player target;
        int money;
        if (player.getServer().getPlayer(args[1]) != null) {
            target = player.getServer().getPlayer(args[1]);
            money = Integer.parseInt(args[2]);
        } else {
            target = player;
            money = Integer.parseInt(args[1]);
        }
        assert target != null;
        player.sendMessage("Has agregado " + money + " a " + target.getName());
    }
}
