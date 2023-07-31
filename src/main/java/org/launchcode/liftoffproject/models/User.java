package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public abstract class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public void setPassword(String password) {
        this.pwHash = encoder.encode(password);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return pwHash;
    }
}
