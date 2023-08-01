package org.launchcode.liftoffproject.models;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Child extends AbstractEntity{

    @NotBlank(message = "Child's first name is required")
    @Size(max = 50, message = "Child's name is too long")
    private String firstName;

    @NotBlank (message = "Child's last name is required")
    @Size (max = 50, message = "Child's last name is too long")
    private String lastName;

    private int points;

    @ManyToOne
    private Parent parent;

    @OneToOne(cascade = CascadeType.ALL)
    private ChildUser userAccount;

    @OneToMany(mappedBy = "childAssigned")
    private List<Chore> chore = new ArrayList<>();

    private String avatar;


    public Child(){}

    public Child(String firstName, String lastName, Parent parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parent = parent;
    }

    public Child(String firstName, String lastName, Parent parent, ChildUser childUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parent = parent;
        this.userAccount = childUser;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public ChildUser getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(ChildUser userAccount) {
        this.userAccount = userAccount;
    }

    public List<Chore> getChore() {
        return chore;
    }

    public void setChore(List<Chore> chore) {
        this.chore = chore;
    }

    public String getAvatar() {return avatar;}

    public void setAvatar(String avatarUrl) {
    }
}
