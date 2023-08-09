package org.launchcode.liftoffproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Chore extends AbstractEntity{


    private String supplies;
    @NotBlank
    private String dayOfTheWeek;
    @NotBlank
    private String name;

    @NotBlank
    private String choreDescription;

    @ManyToOne
    private Child childAssigned;

    private String detailedDescription;

    @ManyToOne
    private Parent parentCreator;

    private boolean completed;

    private int rewardPoints;

//    @Enumerated(EnumType.STRING) // Storing the ENUM as a string in the database
    private DayOfTheWeek dueDay;

    public Chore(){}




    public Chore(String name, String choreDescription, int rewardPoints, DayOfTheWeek dueDay, String dayOfTheWeek, String detailedDescription, String supplies) {
        this.name = name;
        this.choreDescription = choreDescription;
        this.rewardPoints = rewardPoints;
        this.dueDay = dueDay;
        this.dayOfTheWeek = dayOfTheWeek;
        this.supplies = supplies;
        this.completed = false;
    }

    // Getters and Setters

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

    public Child getChildAssigned() {
        return childAssigned;
    }

    public void setChildAssigned(Child childAssigned) {
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

    public DayOfTheWeek getDueDay() {
        return dueDay;
    }

    public void setDueDay(DayOfTheWeek dueDay) {
        this.dueDay = dueDay;
    }

    public String getDayOfTheWeek() {return dayOfTheWeek; }

    public void setDayOfTheWeek(String dayOfTheWeek) {this.dayOfTheWeek = dayOfTheWeek; }

    public String getSupplies() {return supplies; }

    public void setSupplies(String supplies) {this.supplies = supplies; }

    public String getDetailedDescription() {return detailedDescription; }

    public void setDetailedDescription(String detailedDescription) {this.detailedDescription = detailedDescription; }
    @Override
    public String toString() {
        return name;
    }
}
