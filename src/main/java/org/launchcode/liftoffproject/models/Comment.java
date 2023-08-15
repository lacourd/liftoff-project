package org.launchcode.liftoffproject.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Comment extends AbstractEntity {

    @ManyToOne
    private Chore chore;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
    private LocalDate createdDate;
    @Column(columnDefinition = "TEXT")
    private String text;

    public Comment(){

    }


    public Comment(Chore chore, User createdBy, String text){
        this.chore = chore;
        this.createdBy = createdBy;
        this.text = text;

    }

    public Comment(String text) {
        this.text = text;
    }


    public Chore getChore() {
        return chore;
    }

    public void setChore(Chore chore) {
        this.chore = chore;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
