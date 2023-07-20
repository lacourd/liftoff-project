package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ChildUser extends User{

    @OneToOne(mappedBy = "userAccount")
    private Child child;

    public ChildUser() {}

    public ChildUser(String username, String password) {
        super(username, password);
    }
}
