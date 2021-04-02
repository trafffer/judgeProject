package softuni.judgev2.model.service;

import softuni.judgev2.model.entities.Exercise;
import softuni.judgev2.model.entities.User;

import java.time.LocalDateTime;

public class HomeworkServiceModel {
    private Long id;
    private LocalDateTime addedOn;
    private String gitAddress;
    private User author;
    private Exercise exercise;


    public Long getId() {
        return id;
    }

    public HomeworkServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public HomeworkServiceModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkServiceModel setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public HomeworkServiceModel setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public HomeworkServiceModel setExercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }
}
