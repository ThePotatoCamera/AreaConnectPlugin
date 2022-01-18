package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.AreaConnect;
import es.a223.areaconnect.entities.UserLink;
import es.a223.areaconnect.entities.Users;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

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
  public void perform(CommandSender sender, String[] args) {

    if (!sender.hasPermission("areaconnect.link")) {
      sender.sendMessage(ChatColor.RED + "No tienes permisos para usar este comando");
      return;
    }

    if (sender instanceof ConsoleCommandSender) {
      ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
      console.sendMessage(ChatColor.RED + "Este comando solo puede ser ejecutado por un jugador.");
      return;
    }

    String linkCode = generateCode();
    Session dbSession = AreaConnect.dbConnection().openSession();

    Users userObject = new Users();
    userObject.setUserId(Objects.requireNonNull(sender.getServer().getPlayer(sender.getName())).getUniqueId().toString());
    userObject.setUsername(sender.getName());
    dbSession.save(userObject);

    UserLink userLinkObject = new UserLink();
    userLinkObject.setCode(linkCode);
    userLinkObject.setMinecraftUser(userObject);
    dbSession.save(userLinkObject);

    dbSession.close();

    sender.sendMessage(ChatColor.GREEN + "Para vincular tu cuenta, ve al servidor de Discord (" + ChatColor.YELLOW + "https://discord.a223.es" + ChatColor.GREEN + ") y utiliza el siguiente comando:");
    sender.sendMessage(ChatColor.LIGHT_PURPLE + "/mclink " + ChatColor.BOLD + linkCode);

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
