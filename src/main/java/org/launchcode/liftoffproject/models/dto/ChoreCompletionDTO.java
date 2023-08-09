package org.launchcode.liftoffproject.models.dto;

public class ChoreCompletionDTO {
    private int choreId;
    private boolean completed;

    // Getters and setters

    public int getChoreId() {
        return choreId;
    }

    public void setChoreId(int choreId) {
        this.choreId = choreId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

