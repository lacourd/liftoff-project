package org.launchcode.liftoffproject.models;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class ChorePerformance extends AbstractEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String choreDescription;

    @ManyToOne
    private Child childAssigned;

    private Parent parentCreator;

    private boolean completed;

    private int rewardPoints;

    private String dueDay;

    private int performanceRating; // 1-5 stars

    public ChorePerformance() {}

    public ChorePerformance(String name, String choreDescription, int rewardPoints, String dueDay) {
        this.name = name;
        this.choreDescription = choreDescription;
        this.rewardPoints = rewardPoints;
        this.dueDay = dueDay;
        this.completed = false;
        this.performanceRating = 3; // default rating of 3 stars
    }

    public static Iterable<ChorePerformance> findAll() {
        return null;
    }

    public static ChorePerformance findById(Integer id) {
        return null;
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

    public String getDueDay() {
        return dueDay;
    }

    public void setDueDay(String dueDay) {
        this.dueDay = dueDay;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    @Override
    public String toString(){
        return name;
    } // <-- Added a return statement here
}