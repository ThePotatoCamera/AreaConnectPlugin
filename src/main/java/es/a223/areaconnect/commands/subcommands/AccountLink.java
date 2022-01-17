package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.AreaConnect;
import es.a223.areaconnect.entities.UserLink;
import es.a223.areaconnect.entities.Users;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;

/**
 * Comando para enlazar cuentas
 */
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

        if (!player.hasPermission("areaconnect.link")) {
            player.sendMessage(ChatColor.RED + "No tienes permisos para usar este comando");
            return;
        }

        if (player instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            console.sendMessage(ChatColor.RED + "Este comando solo puede ser ejecutado por un jugador.");
            return;
        }

        String linkCode = generateCode();
        Session dbSession = AreaConnect.dbConnection().openSession();

        Users userObject = new Users();
        userObject.setUserId(player.getUniqueId().toString());
        userObject.setUsername(player.getName());
        dbSession.save(userObject);

        UserLink userLinkObject = new UserLink();
        userLinkObject.setCode(linkCode);
        userLinkObject.setMinecraftUser(userObject);
        dbSession.save(userLinkObject);

        dbSession.close();

        player.sendMessage(ChatColor.GREEN + "Para vincular tu cuenta, ve al servidor de Discord (" + ChatColor.YELLOW + "https://discord.a223.es" + ChatColor.GREEN + ") y utiliza el siguiente comando:");
        player.sendMessage(ChatColor.LIGHT_PURPLE + "/mclink " + ChatColor.BOLD + linkCode);

    }
    
    private String generateCode() {
        int length = 6;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * 10);
            code.append(random);
        }
        return code.toString();
    }
}
