package es.a223.areaconnect.entities;

import javax.persistence.*;

/**
 * Clase de la entidad UserLink
 */
@Entity
@Table(name = "areacon_user_link")
public class UserLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "minecraft_user")
    private Users minecraftUser;

    @Column(name = "discord_id")
    private String discordId;

    @Column(name = "code")
    private String code;

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets discord id.
     *
     * @return the discord id
     */
    public String getDiscordId() {
        return discordId;
    }

    /**
     * Sets discord id.
     *
     * @param discordId the discord id
     */
    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    /**
     * Gets minecraft user.
     *
     * @return the minecraft user
     */
    public Users getMinecraftUser() {
        return minecraftUser;
    }

    /**
     * Sets minecraft user.
     *
     * @param minecraftUser the minecraft user
     */
    public void setMinecraftUser(Users minecraftUser) {
        this.minecraftUser = minecraftUser;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}