package org.launchcode.liftoffproject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent extends AbstractEntity {

    @NotBlank (message = "First Name is required")
    @Size (max = 50, message = "Name is too long")
    private String firstName;

    @NotBlank (message = "Last Name is required")
    @Size (max = 50, message = "Last Name is too long")
    private String lastName;

    @OneToMany(mappedBy = "parent")
    private static List<Child> children = new ArrayList<>();

    @OneToMany(mappedBy = "parentCreator")
    private List<Chore> chores = new ArrayList<>();

    @OneToMany(mappedBy = "parentCreator")
    private List<Reward> rewards = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private ParentUser userAccount;

    public Parent() {}

    public Parent(String firstName, String lastName, ParentUser parentUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAccount = parentUser;
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

    public static List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public List<Reward> getRewards(String username) {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public ParentUser getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(ParentUser userAccount) {
        this.userAccount = userAccount;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}

