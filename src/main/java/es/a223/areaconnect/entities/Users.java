package es.a223.areaconnect.entities;

import javax.persistence.*;

/**
 * Clase de la entidad Users
 */
@Entity
@Table(name = "areacon_users")
public class Users {
    @Id
    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "username")
    private String username;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    private Integer money;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}