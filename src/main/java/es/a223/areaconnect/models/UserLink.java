package es.a223.areaconnect.models;

import javax.persistence.*;

@Entity
@Table(name = "user_link")
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public Users getMinecraftUser() {
        return minecraftUser;
    }

    public void setMinecraftUser(Users minecraftUser) {
        this.minecraftUser = minecraftUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}