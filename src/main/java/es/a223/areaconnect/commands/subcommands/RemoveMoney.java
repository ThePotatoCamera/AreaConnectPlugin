package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.AreaConnect;
import es.a223.areaconnect.entities.Users;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;

import java.util.List;

public class RemoveMoney extends SubCommand {
  /**
   * Gets name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return "removemoney";
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  @Override
  public String getDescription() {
    return "Retira dinero de un jugador";
  }

  /**
   * Gets usage.
   *
   * @return the usage
   */
  @Override
  public String getUsage() {
    return "/areaconnect removemoney [jugador] <dinero>";
  }

  /**
   * Perform.
   *
   * @param player the player
   * @param args   the args
   */
  @Override
  public void perform(CommandSender player, String[] args) {
    if (args.length < 2) {
      player.sendMessage(ChatColor.GOLD + "Uso: " + getUsage());
      return;
    }
    if (!player.hasPermission("areaconnect.removemoney")) {
      player.sendMessage(ChatColor.RED + "No tienes permiso para usar este comando");
      return;
    }
    Player target;
    int money;
    if (player.getServer().getPlayer(args[1]) != null) {
      target = player.getServer().getPlayer(args[1]);
      money = Integer.parseInt(args[2]);
    } else if (player instanceof ConsoleCommandSender) {
      if (player.getServer().getPlayer(args[1]) == null) {
        player.sendMessage(ChatColor.RED + "Debes especificar un jugador desde la consola.");
        return;
      }
      target = player.getServer().getPlayer(args[1]);
      money = Integer.parseInt(args[2]);
    }
    else {
      target = (Player) player;
      money = Integer.parseInt(args[1]);
    }
    assert target != null;

    Session session = AreaConnect.dbConnection().openSession();
    Users user = session.get(Users.class, target.getUniqueId().toString());

    user.setMoney(user.getMoney() - money);

    session.save(user);
    session.close();

    player.sendMessage(ChatColor.GREEN + "Se han retirado " + money + " a " + target.getName());
  }

  @Override
  public List<String> getCompletions(CommandSender sender, int argindex, String[] args) {
    return null;
  }
}
