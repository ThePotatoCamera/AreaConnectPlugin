package es.a223.areaconnect.commands.subcommands;

import es.a223.areaconnect.AreaConnect;
import es.a223.areaconnect.entities.Users;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class AccountUnlink extends SubCommand {
    /**
     * Gets name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "unlink";
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Desvincula una cuenta de Minecraft de una cuenta de Discord";
    }

    /**
     * Gets usage.
     *
     * @return the usage
     */
    @Override
    public String getUsage() {
        return "/a223 unlink";
    }

    /**
     * Perform.
     *
     * @param player the player
     * @param args   the args
     */
    @Override
    public void perform(Player player, String[] args) {

        if (!player.hasPermission("areaconnect.unlink")) {
            player.sendMessage(ChatColor.RED + "No tienes permisos para usar este comando");
            return;
        }

        if (player == null) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            console.sendMessage(ChatColor.RED + "Este comando solo puede ser ejecutado por un jugador.");
            return;
        }

        Session dbSession = AreaConnect.dbConnection().openSession();

        Users userObject = dbSession.get(Users.class, player.getUniqueId().toString());
        deleteLink(userObject.getUserId());

        dbSession.close();

        player.sendMessage(ChatColor.GREEN + "Has desvinculado tu cuenta de Minecraft de Discord.");
    }

    @Transactional
    private void deleteLink(String minecraft_user) {
        EntityManager em = AreaConnect.dbConnection().createEntityManager();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("DELETE FROM areaconnect.user_link WHERE minecraft_user = :minecraft_user");
        query.setParameter("minecraft_user", minecraft_user);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}
