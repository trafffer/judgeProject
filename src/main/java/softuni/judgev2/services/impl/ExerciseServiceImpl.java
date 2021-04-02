package softuni.judgev2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.judgev2.model.entities.Exercise;
import softuni.judgev2.model.service.ExerciseServiceModel;
import softuni.judgev2.repositories.ExerciseRepository;
import softuni.judgev2.services.ExerciseService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository repository;
    private final ModelMapper mapper;


    public ExerciseServiceImpl(ExerciseRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addEx(ExerciseServiceModel model) {
        repository.save(mapper.map(model, Exercise.class));
    }

    @Override
    public List<String> findAllExNames() {
        return repository.findAllExNames();
    }

    @Override
    public boolean check(String exercise) {
        Exercise exerciseEntity = repository.findByName(exercise).orElse(null);
        return exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public Exercise findByName(String exercise) {
        return repository.findByName(exercise).orElse(null);
    }
}
