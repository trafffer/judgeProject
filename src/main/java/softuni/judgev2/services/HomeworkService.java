package softuni.judgev2.services;

import softuni.judgev2.model.entities.Homework;
import softuni.judgev2.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void addHomework(String exercise, String gitAddress);

    HomeworkServiceModel findHomeworkforScoring();

    Homework findById(Long homeworkId);
}
