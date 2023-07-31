package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Reward extends AbstractEntity {

    private int points;

    @NotBlank(message = "Reward name is required")
    private String name;

    private String description;

    public Reward(String name, int points, String description) {
        this.name = name;
        this.points = points;
        this.description = description;
    }

    public Reward() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @Override
//    public void setUsername(String username) {
//
//    }
//
//    @Override
//    public void setPassword(String encodedPassword) {
//
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
}
