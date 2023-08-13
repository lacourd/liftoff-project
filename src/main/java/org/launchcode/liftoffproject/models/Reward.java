package org.launchcode.liftoffproject.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Reward extends AbstractEntity {

    private int points;

    @NotBlank(message = "Reward name is required")
    private String name;

    private String description;

    @ManyToOne
    private Parent parentCreator;

    // points redemption
    private boolean redeemed;

    private boolean fulfilled;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate redemptionDate;

    @ManyToOne
    private Child child;

    public Reward(String name, int points, String description) {
        this.name = name;
        this.points = points;
        this.description = description;
        this.redeemed = false;
        this.fulfilled = false;
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

    public Parent getParentCreator() {
        return parentCreator;
    }

    public void setParentCreator(Parent parentCreator) {
        this.parentCreator = parentCreator;
    }

    // Getters and setters

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    //points redemption
    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }

    public LocalDate getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(LocalDate redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}

