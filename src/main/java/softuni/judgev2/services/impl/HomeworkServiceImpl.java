package softuni.judgev2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.judgev2.model.entities.Homework;
import softuni.judgev2.model.service.HomeworkServiceModel;
import softuni.judgev2.repositories.HomeworkRepository;
import softuni.judgev2.security.CurrentUser;
import softuni.judgev2.services.ExerciseService;
import softuni.judgev2.services.HomeworkService;
import softuni.judgev2.services.UserService;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository repository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper mapper;

    public HomeworkServiceImpl(HomeworkRepository repository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService, ModelMapper mapper) {
        this.repository = repository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
        this.mapper = mapper;
    }


    @Override
    public void addHomework(String exercise, String gitAddress) {
        Homework homework = new Homework();
        homework.setGitAddress(gitAddress);
        homework.setAddedOn(LocalDateTime.now());
        homework.setExercise(exerciseService.findByName(exercise));
        homework.setAuthor(userService.findById(currentUser.getId()));
        repository.save(homework);
    }

    @Override
    public HomeworkServiceModel findHomeworkforScoring() {
         return repository.findTop1ByOrderByComments()
                 .map(homework -> mapper.map(homework,HomeworkServiceModel.class))
                 .orElse(null);
    }

    @Override
    public Homework findById(Long homeworkId) {
        return repository.findById(homeworkId).orElse(null);
    }
}
