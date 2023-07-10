package org.launchcode.liftoffproject.models;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Parent extends User {

    @NotBlank (message = "First Name is required")
    @Size (max = 50, message = "Name is too long")
    private String firstName;

    @NotBlank (message = "Last Name is required")
    @Size (max = 50, message = "Last Name is too long")
    private String lastName;



    @ManyToOne
    public void Child() {
        ArrayList<Object> children = new ArrayList<>();
    }

    public void Chores() {
        ArrayList<Object> chores = new ArrayList<>();
    }

    public Parent(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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


}
