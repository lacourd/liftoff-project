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

    private String linkedInUrl;

    private String gitHubUrl;

    public Author(){
    }

    public Author(String name, String imageUrl, String descriptionOfFeatures, String bio, String linkedInUrl, String gitHubUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.descriptionOfFeatures = descriptionOfFeatures;
        this.bio = bio;
        this.linkedInUrl = linkedInUrl;
        this.gitHubUrl= gitHubUrl;

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

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }
}
