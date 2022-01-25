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

public class AddMoney implements SubCommand {
  /**
   * Gets name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return "addmoney";
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
    return "/areaconnect addmoney [jugador] <dinero>";
  }

  /**
   * Perform.
   *
   * @param sender the sender
   * @param args   the args
   */
  @Override
  public void perform(CommandSender sender, String[] args) {
    if (args.length < 2) {
      sender.sendMessage(ChatColor.GOLD + "Uso: " + getUsage());
      return;
    }
    if (!sender.hasPermission("areaconnect.addmoney")) {
      sender.sendMessage(ChatColor.RED + "No tienes permiso para usar este comando");
      return;
    }
    Player target;
    int money;
    if (sender.getServer().getPlayer(args[1]) != null) {
      target = sender.getServer().getPlayer(args[1]);
      money = Integer.parseInt(args[2]);
    } else if (sender instanceof ConsoleCommandSender) {
      if (sender.getServer().getPlayer(args[1]) == null) {
        sender.sendMessage(ChatColor.RED + "Debes especificar un jugador desde la consola.");
        return;
      }
      target = sender.getServer().getPlayer(args[1]);
      money = Integer.parseInt(args[2]);
    }
    else {
      target = (Player) sender;
      money = Integer.parseInt(args[1]);
    }
    assert target != null;

    Session session = AreaConnect.dbConnection().openSession();
    Users user = session.get(Users.class, target.getUniqueId().toString());

    user.setMoney(user.getMoney() + money);

    session.save(user);
    session.close();

    sender.sendMessage(ChatColor.GREEN + "Se han añadido " + money + " a " + target.getName());
  }
}
