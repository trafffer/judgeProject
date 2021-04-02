package softuni.judgev2.services;

import softuni.judgev2.model.entities.Exercise;
import softuni.judgev2.model.service.ExerciseServiceModel;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseService {
    void addEx(ExerciseServiceModel model);

    List<String> findAllExNames();

    boolean check(String name);

    Exercise findByName(String exercise);
}
