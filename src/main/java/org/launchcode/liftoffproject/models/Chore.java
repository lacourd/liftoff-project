package org.launchcode.liftoffproject.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chore extends AbstractEntity {


    private String supplies;

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

    private boolean approvedByParent;

    private int rewardPoints;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @OneToMany(mappedBy = "chore", cascade = CascadeType.ALL)
    private List<ChoreCompletion> completions;

    public Chore() {
    }

    @OneToMany(mappedBy = "chore")
    private List<Comment> comments = new ArrayList<>();

    public Chore(String name, String choreDescription, int rewardPoints, LocalDate dueDate, String supplies) {
        this.name = name;
        this.choreDescription = choreDescription;
        this.rewardPoints = rewardPoints;
        this.dueDate = dueDate;
        this.supplies = supplies;
        this.completed = false;
        this.approvedByParent = false;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<ChoreCompletion> getCompletions() {
        return completions;
    }

    public void setCompletions(List<ChoreCompletion> completions) {
        this.completions = completions;
    }

    public boolean isApprovedByParent() {
        return approvedByParent;
    }

    public void setApprovedByParent(boolean approvedByParent) {
        this.approvedByParent = approvedByParent;
    }

    public String getSupplies() {return supplies; }

    public void setSupplies(String supplies) {this.supplies = supplies; }

    public String getDetailedDescription() {return detailedDescription; }

    public void setDetailedDescription(String detailedDescription) {this.detailedDescription = detailedDescription; }

    @Override
    public String toString() {
        return name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
