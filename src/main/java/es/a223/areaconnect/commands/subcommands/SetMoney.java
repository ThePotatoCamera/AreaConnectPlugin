package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.AreaConnect;
import es.a223.areaconnect.entities.Users;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;

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
  public void perform(Player player, String[] args) {
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
    }
    else {
      target = player;
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
}
