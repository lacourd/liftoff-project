package org.launchcode.liftoffproject.models;

import javax.validation.constraints.NotBlank;

public class Author {

    @NotBlank
    private String name;

    private String imageUrl;

    @NotBlank
    private String descriptionOfFeatures;

    @NotBlank
    private String bio;

    public Author(){
    }

    public Author(String name, String imageUrl, String descriptionOfFeatures, String bio) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.descriptionOfFeatures = descriptionOfFeatures;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescriptionOfFeatures() {
        return descriptionOfFeatures;
    }

    public void setDescriptionOfFeatures(String descriptionOfFeatures) {
        this.descriptionOfFeatures = descriptionOfFeatures;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
