package es.a223.areaconnect.commands;

import es.a223.areaconnect.commands.subcommands.Reload;
import es.a223.areaconnect.commands.subcommands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new Reload());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "Comandos disponibles:");
                for (SubCommand subCommand : subCommands) {
                    player.sendMessage(ChatColor.YELLOW + "/" + subCommand.getName() + ": " + ChatColor.WHITE + subCommand.getDescription());
                }
            }
            else {
                for (SubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.perform(player, args);
                    }
                }
            }
        }
            return true;
    }


    public ArrayList<SubCommand> getSubcommands() {
        return subCommands;
    }
}
