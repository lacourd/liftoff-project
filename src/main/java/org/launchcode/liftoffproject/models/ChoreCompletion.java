package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class ChoreCompletion extends AbstractEntity {
    @ManyToOne
    private Child child;

    @ManyToOne
    private Chore chore;

    @ManyToOne
    private Parent parent;

    private boolean completedByChild;

    private boolean approvedByParent;

    private LocalDate completionDate;

    // Constructors, getters, and setters...


    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
    public Chore getChore() {
        return chore;
    }

    public void setChore(Chore chore) {
        this.chore = chore;
    }
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public boolean isCompletedByChild() {
        return completedByChild;
    }

    public void setCompletedByChild(boolean completedByChild) {
        this.completedByChild = completedByChild;
    }

    public boolean isApprovedByParent() {
        return approvedByParent;
    }

    public void setApprovedByParent(boolean approvedByParent) {
        this.approvedByParent = approvedByParent;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
