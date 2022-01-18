package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.AreaConnect;
import es.a223.areaconnect.entities.Users;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class SetMoney extends SubCommand {
  /**
   * Gets name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return "setmoney";
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  @Override
  public String getDescription() {
    return "Configura el dinero de un jugador";
  }

  /**
   * Gets usage.
   *
   * @return the usage
   */
  @Override
  public String getUsage() {
    return "/areaconnect setmoney [jugador] <dinero>";
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
    if (!player.hasPermission("areaconnect.setmoney")) {
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
    } else {
      target = (Player) player;
      money = Integer.parseInt(args[1]);
    }
    assert target != null;

    Session session = AreaConnect.dbConnection().openSession();
    Users user = session.get(Users.class, target.getUniqueId());

    user.setMoney(money);

    session.save(user);
    session.close();

    player.sendMessage(ChatColor.GREEN + "Se ha configurado a " + money + " el dinero de " + target.getName());
  }

  @Override
  public List<String> getCompletions(CommandSender sender, int argindex, String[] args) {
    List<String> completions = new ArrayList<String>();
    for (Player player : sender.getServer().getOnlinePlayers()) {
      completions.add(player.getName());
    }

    List<String> results = new ArrayList<String>();

    if (argindex == 2) {
      for (String c : completions) {
        if (c.toLowerCase().startsWith(args[1].toLowerCase())) {
          results.add(c);
        }
      }
      results.add("1"); results.add("10");
      results.add("100"); results.add("1000");
      return results;
    }

    if (argindex == 3) {
      results.add("1"); results.add("10");
      results.add("100"); results.add("1000");
      return results;
    }

    return null;
  }
}
