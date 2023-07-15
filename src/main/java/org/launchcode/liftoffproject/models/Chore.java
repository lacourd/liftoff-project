package org.launchcode.liftoffproject.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Chore extends AbstractEntity{
    @Size(min = 1, max = 50)
    @NotBlank
    private String name;
    public Chore(String name) {this.name = name;}
    public Chore() {}
    public String getName() {return name; }
    public void setName(String name) { this.name = name; }

}
