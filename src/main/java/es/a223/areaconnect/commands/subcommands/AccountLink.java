package es.a223.areaconnect.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AccountLink extends SubCommand{
    @Override
    public String getName() {
        return "link";
    }

    @Override
    public String getDescription() {
        return "Vincula la cuenta de Minecraft con la de Discord";
    }

    @Override
    public String getUsage() {
        return "/areaconnect link";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("areaconnect.link")) {
            String playeruuid = player.getUniqueId().toString();
            player.sendMessage(ChatColor.GREEN + "Para vincular tu cuenta, ve al servidor de Discord (" + ChatColor.YELLOW + "https://discord.a223.es" + ChatColor.GREEN + ") y utiliza el comando mclink introduciendo el siguiente comando:");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "/mclink " + ChatColor.BOLD + playeruuid);
        }
    }
}
