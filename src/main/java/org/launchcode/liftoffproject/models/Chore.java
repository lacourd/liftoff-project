package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chore extends AbstractEntity{

    @NotBlank
    private String name;

    @NotBlank
    private String choreDescription;

//    @ManyToMany
    private List<Child> childAssigned = new ArrayList<>();

    private Parent parentCreator;

    private boolean completed;

    private int rewardPoints;

    private String dueDay;

    public Chore(){}

    public Chore(String name, String choreDescription, int rewardPoints, String dueDay) {
        this.name = name;
        this.choreDescription = choreDescription;
         this.rewardPoints = rewardPoints;
        this.dueDay = dueDay;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoreDescription() {
        return choreDescription;
    }

    public void setChoreDescription(String choreDescription) {
        this.choreDescription = choreDescription;
    }

    public List<Child> getChildAssigned() {
        return childAssigned;
    }

    public void setChildAssigned(List<Child> childAssigned) {
        this.childAssigned = childAssigned;
    }

    public Parent getParentCreator() {
        return parentCreator;
    }

    public void setParentCreator(Parent parentCreator) {
        this.parentCreator = parentCreator;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getDueDay() {
        return dueDay;
    }

    public void setDueDay(String dueDay) {
        this.dueDay = dueDay;
    }

    @Override
    public String toString(){ return name; }
}
