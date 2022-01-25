package es.a223.areaconnect.integration;

import es.a223.areaconnect.AreaConnect;
import org.bukkit.entity.Player;
import org.hibernate.Session;

import java.awt.geom.Area;

/**
 * Realiza acciones sobre el usuario de Minecraft.
 */
public class UserActions {

  /**
   * Recupera la ID de Discord del usuario.
   *
   * @param userMinecraft the user minecraft
   * @return the user discord id
   */
  public static String getUserDiscordId(Player userMinecraft) {
    if (userMinecraft == null) {
      throw new IllegalArgumentException("El usuario no puede ser nulo.");
    }

    Session session = AreaConnect.dbConnection().openSession();


    return null;
  }
}
