package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reward extends AbstractEntity {

    private int points;

    @NotBlank(message = "Reward name is required")
    private String name;

    private String description;

//    Assign reward to chore?
//    @ManyToMany(mappedBy = "rewards")
//    private final List<Chore> chores = new ArrayList<>();

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

    //    public List<Chore> getChores() {return chores;}

}
