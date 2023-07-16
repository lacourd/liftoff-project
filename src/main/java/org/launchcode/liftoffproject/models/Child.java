package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Child extends User {

    @NotBlank(message = "Child's first name is required")
    @Size(max = 50, message = "Child's name is too long")
    private String firstName;

    @NotBlank (message = "Child's last name is required")
    @Size (max = 50, message = "Child's last name is too long")
    private String lastName;

    private int points;

    @ManyToOne
    private Parent parent;

    public Child(){}

    public Child(String firstName, String lastName, Parent parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parent = parent;
    }

    public Child(String username, String password, String firstName, String lastName, Parent parent) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.parent = parent;
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
}
